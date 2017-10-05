package com.softserve.edu.Resources.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.softserve.edu.Resources.dto.ResourceCategoryDTO;
import com.softserve.edu.Resources.dto.Views;
import com.softserve.edu.Resources.entity.ResourceCategory;
import com.softserve.edu.Resources.service.PropertyService;
import com.softserve.edu.Resources.service.impl.ResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
@Controller
@Transactional
@RequestMapping("/resources")
public class ResourceTypeManagementController {

    @Autowired
    private PropertyService propertyService;

    @Autowired
    ResourceCategoryService categoryService;

    @Autowired
    private Environment env;

    private static boolean alreadyExecuted = false;

    @RequestMapping(value = "/editType", method = RequestMethod.GET)
    public String editResource(@RequestParam(value = "id", defaultValue = "0") long id, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("env", env);
        if (id == 0) {
            return "editType";
        } else {
            ////todo: populate resourceType{id}
            return "editType";
        }
    }

    @RequestMapping(value = "/addType", method = RequestMethod.GET)
    public String addResource(Model model) {
        model.addAttribute("id", 0);
        return "editType";
    }

    @ResponseBody
    @JsonView(Views.CategoriesWithTypes.class)
    @RequestMapping(value = "/categorizedTypes", method = RequestMethod.GET)
    public List<ResourceCategoryDTO> categorizedTypes() {
        ResourceCategory root = new ResourceCategory();
        if (!alreadyExecuted) {
            root = categoryService.insertCategoriesTEMPORARY();
            alreadyExecuted = true;
        }
        List<ResourceCategory> rootCategories = categoryService.findRootCategories();
        List<ResourceCategoryDTO> categoryDTOS = rootCategories.stream()
                .map(categoryService::createCategoryDTO)
                .collect(Collectors.toList());

/*        ResourceCategoryDTO dto = categoryService.createCategoryDTO((ResourceCategory) ((ResourceCategory) rootCategories.get(0).getChildrenCategories().toArray()[0]).getChildrenCategories().toArray()[0]);
        System.out.println(dto);
        ResourceCategory trans = categoryService.mapFromDtoToResourceCategory(dto);
        System.out.println(trans);*/

        return categoryDTOS;
    }

    @ResponseBody
    @JsonView(Views.Categories.class)
    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public List<ResourceCategoryDTO> categories() {
        return categorizedTypes();
    }

    @ResponseBody
    @RequestMapping(value = "/categories", method = RequestMethod.POST)
    public void updateCategoriesHierarchy(@RequestBody List<ResourceCategoryDTO> categoryDTOList ) {
        List<ResourceCategory> rootCategoriesFromWeb = categoryDTOList.stream()
                .map(categoryService::mapFromDtoToResourceCategory)
                .collect(Collectors.toList());
        if (!categoryService.hasCycleDependencies(categoryService.deployAllCategoriesFromRoots(rootCategoriesFromWeb))) {
            categoryService.deleteMissingCategoriesInDB(rootCategoriesFromWeb);
            rootCategoriesFromWeb.forEach(categoryService::saveResourceCategory);
        } else {
            System.out.println("CYCLE!");
//            throw new RuntimeException("Can not save hierarchy of Resource Categories with cycle dependencies");
        }
    }

}
