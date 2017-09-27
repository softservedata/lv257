package com.softserve.edu.Resources.controller;

import com.softserve.edu.Resources.entity.ResourceCategory;
import com.softserve.edu.Resources.entity.ResourceProperty;
import com.softserve.edu.Resources.service.PropertyService;
import com.softserve.edu.Resources.service.impl.ResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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

    private static boolean alreadyExecuted = false;

    @RequestMapping(value = "/editType", method = RequestMethod.GET)
    public String editResource(@RequestParam(value = "id", defaultValue = "0") long id) {
        if (id == 0) {
            return "editType";
        } else {
            ////todo: populate resourceType{id}
            return "editType";
        }
    }

    @RequestMapping(value = "/addType", method = RequestMethod.GET)
    public String addResource(Model model) {
        if(!alreadyExecuted) {
            categoryService.insertCategoriesTEMPORARY();
            alreadyExecuted = true;
        }
        List<ResourceCategory> rootCategories = categoryService.getRootsFromDB();
        model.addAttribute("inputJson", categoryService.serializeCategoriesIntoJson(rootCategories));
        List<ResourceProperty> properties = propertyService.getProperties();
        model.addAttribute("properties", properties);
        return "editType";
    }

    @RequestMapping(value = "/manageTypes", method = RequestMethod.GET)
    public ModelAndView manageTypes() {
        ModelAndView model = new ModelAndView("manageTypes");
        if(!alreadyExecuted) {
            categoryService.insertCategoriesTEMPORARY();
            alreadyExecuted = true;
        }
        List<ResourceCategory> rootCategories = categoryService.getRootsFromDB();
        model.addObject("inputJson", categoryService.serializeCategoriesIntoJson(rootCategories));
        return model;
    }

    @ResponseBody
    @RequestMapping(value = "/manageTypes", method = RequestMethod.POST)
    public void saveResultsOfManagingTypes(@RequestBody String outputJson) {
        List<ResourceCategory> rootCategoriesFromWeb = categoryService.deserializeCategoriesFromJson(outputJson);
        if (!categoryService.hasCycleDependencies1(rootCategoriesFromWeb)) {
            categoryService.deleteMissingCategoriesInDB(rootCategoriesFromWeb);
            rootCategoriesFromWeb.forEach(categoryService::saveResourceCategory);
        } else {
            System.out.println("CYCLE!");
//            throw new RuntimeException("Can not save hierarchy of Resource Categories with cycle dependencies");
        }
    }
}
