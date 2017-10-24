package com.softserve.edu.Resources.rest;

import com.softserve.edu.Resources.dto.ResourceTypeBrief;
import com.softserve.edu.Resources.dto.ViewTypesDTO;
import com.softserve.edu.Resources.entity.ResourceCategory;
import com.softserve.edu.Resources.entity.ResourceType;
import com.softserve.edu.Resources.exception.InvalidResourceCategoryException;
import com.softserve.edu.Resources.exception.ResourceTypeNotFoundException;
import com.softserve.edu.Resources.service.ResourceCategoryService;
import com.softserve.edu.Resources.service.ResourceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api", method = RequestMethod.GET)
public class ResourcesRestController {

    @Autowired
    ResourceTypeService resourceTypeService;

    @Autowired
    ResourceCategoryService resourceCategoryService;

    @RequestMapping(value = "/resource", method = RequestMethod.POST)
    public ResourceTypeBrief addResourceType(@RequestBody ResourceTypeBrief resourceTypeBrief) {
        ResourceType resourceType = resourceTypeService.save(resourceTypeBrief);
        return new ResourceTypeBrief(resourceType);
    }

    @RequestMapping(value = "/resource/{id}", method = RequestMethod.GET)
    public ResourceTypeBrief getResourceType(@PathVariable Long id) {
        Optional<ResourceType> resourceType = resourceTypeService.get(id, true);
        if (!resourceType.isPresent())
            throw new ResourceTypeNotFoundException("Requested Resource Type not found.");
        return new ResourceTypeBrief(resourceType.get());
    }

    @RequestMapping(value = "/getTypes", method = RequestMethod.GET)
    public List<ViewTypesDTO> getTypesByCategory(@RequestParam("id") Optional<Long> id) {
        return resourceCategoryService.getTypesByCategoryId(id).stream()
                .map(ViewTypesDTO::new).collect(Collectors.toList());
    }
}
