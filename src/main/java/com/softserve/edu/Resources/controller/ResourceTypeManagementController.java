package com.softserve.edu.Resources.controller;

import com.softserve.edu.Resources.entity.ResourceProperty;
import com.softserve.edu.Resources.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 *
 */
@Controller
@RequestMapping("/resources")
public class ResourceTypeManagementController {

    @Autowired
    private PropertyService propertyService;

    @RequestMapping(value = "/editType", method = RequestMethod.GET)
    public String editResource(@RequestParam(value = "id") long id) {
        return "editType";
    }

    @RequestMapping(value = "/addType", method = RequestMethod.GET)
    public String addResource(Model model) {
        List<ResourceProperty> properties = propertyService.getProperties();
        model.addAttribute("properties", properties);
        return "editType";
    }
}
