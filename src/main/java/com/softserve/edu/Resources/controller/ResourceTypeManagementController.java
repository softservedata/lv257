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
    private Environment env;

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
}
