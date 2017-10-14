package com.softserve.edu.Resources.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.softserve.edu.Resources.dto.ExceptionJSONInfo;
import com.softserve.edu.Resources.dto.ResourceCategoryDTO;
import com.softserve.edu.Resources.dto.Views;
import com.softserve.edu.Resources.entity.ResourceCategory;
import com.softserve.edu.Resources.exception.BadCategoryNameException;
import com.softserve.edu.Resources.exception.CycleDependencyException;
import com.softserve.edu.Resources.exception.RemovingCategoriesWithTypesException;
import com.softserve.edu.Resources.service.PropertyService;
import com.softserve.edu.Resources.service.ResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
//        model.addAttribute("env", env);
        if (id == 0) {
            return "editType";
        } else {
            model.addAttribute("id", id);
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
        if (!alreadyExecuted) {
            categoryService.insertCategoriesTEMPORARY();
            alreadyExecuted = true;
        }
        List<ResourceCategory> rootCategories = categoryService.findRootCategories();
        List<ResourceCategoryDTO> categoryDTOS = rootCategories.stream()
                .map(categoryService::createCategoryDTO)
                .collect(Collectors.toList());
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
    public void updateCategoriesHierarchy(@RequestBody List<ResourceCategoryDTO> categoryDTOList) {
        List<ResourceCategory> rootCategoriesFromWeb = categoryDTOList.stream()
                .map(categoryService::mapFromDtoToResourceCategory)
                .collect(Collectors.toList());
        List<ResourceCategory> allCategoriesFromWeb = categoryService.deployAllCategoriesFromRoots(rootCategoriesFromWeb);
        if (!categoryService.isValidCategoryName(allCategoriesFromWeb, 3, 50)) {
            throw new BadCategoryNameException("Some categories have identical names or their names are incorrect");
        }
        categoryService.deleteMissingCategoriesInDB(allCategoriesFromWeb);
        rootCategoriesFromWeb.forEach(categoryService::saveResourceCategory);
    }

    @ExceptionHandler(CycleDependencyException.class)
    @ResponseStatus(value= HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionJSONInfo handleCycleDependenciesException(HttpServletRequest request, Exception ex) {
        ExceptionJSONInfo response = new ExceptionJSONInfo();
        response.setUrl(request.getRequestURL().toString());
        response.setMessage(ex.getMessage() + ". Check all categories and try again");
        return response;
    }

    @ExceptionHandler(BadCategoryNameException.class)
    @ResponseStatus(value= HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionJSONInfo handleBadCategoryNameException(HttpServletRequest request, Exception ex) {
        ExceptionJSONInfo response = new ExceptionJSONInfo();
        response.setUrl(request.getRequestURL().toString());
        response.setMessage(ex.getMessage() + ". Check that all categories have correct and unique names and try again");
        return response;
    }

    @ExceptionHandler(RemovingCategoriesWithTypesException.class)
    @ResponseStatus(value= HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionJSONInfo handleRemovingCategoriesWithTypesException(HttpServletRequest request, Exception ex) {
        ExceptionJSONInfo response = new ExceptionJSONInfo();
        response.setUrl(request.getRequestURL().toString());
        response.setMessage(ex.getMessage() + ". Check that all categories, which you want remove, haven't resource types");
        return response;
    }
}
