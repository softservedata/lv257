package com.softserve.edu.Resources.controller;

import com.softserve.edu.Resources.dto.DtoUtilMapper;
import com.softserve.edu.Resources.dto.GenericResourceDTO;
import com.softserve.edu.Resources.dto.ResourceTypeDTO;
import com.softserve.edu.Resources.entity.GenericResource;
import com.softserve.edu.Resources.entity.ResourceProperty;
import com.softserve.edu.Resources.entity.ResourceType;
import com.softserve.edu.Resources.service.ResourceService;
import com.softserve.edu.Resources.service.ResourceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LookUpController {
    
    @Autowired
    ResourceTypeService resourceTypeService;
    
    @Autowired
    ResourceService resourceService;


    @RequestMapping(value = "/lookUp/resourceTypes", method = RequestMethod.GET)
    public List<ResourceTypeDTO> loadResourceTypes(){


        return DtoUtilMapper.resTypesToResTypesDTO(resourceTypeService.getInstances());

    }
    
    @RequestMapping(value = "/lookUp/resourceProperties/{resourceTypeId}", method = RequestMethod.GET)

    public List<ResourceProperty> loadSpecResourceProperty(@PathVariable String resourceTypeId){
        

        ResourceType resourceType = resourceTypeService.findWithPropertiesByID(Long.parseLong(resourceTypeId));

        // if the list is empty, send an error/message
        
        return resourceTypeService.getSearchableProperties(resourceType);

    }
    
    
    @RequestMapping(value = "/lookUp/inputValues", method = RequestMethod.POST)
    public List<GenericResource> getValuesFromForm(@RequestBody GenericResourceDTO resourceDTO){

        // if the list is empty, send a message that no info hasn't been found
        return resourceService.findResourcesByResourceType(resourceDTO);
    }
    
    
}
