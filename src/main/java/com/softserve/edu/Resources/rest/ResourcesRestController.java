package com.softserve.edu.Resources.rest;

import com.softserve.edu.Resources.dto.ResourceTypeUpdate;
import com.softserve.edu.Resources.entity.ResourceType;
import com.softserve.edu.Resources.service.ResourceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/resource")
public class ResourcesRestController {

    @Autowired
    ResourceTypeService resourceTypeService;

    @RequestMapping(method = RequestMethod.POST)
    public ResourceType addResourceProperty(@RequestBody ResourceTypeUpdate resourceTypeUpdate) {
        ResourceType resourceType = resourceTypeService.save(resourceTypeUpdate);
        return resourceType;
    }
}
