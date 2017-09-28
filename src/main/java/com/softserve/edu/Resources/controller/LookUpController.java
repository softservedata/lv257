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
    ResourceTypeService resTypeService;
    
    @Autowired
    ResourceService resourceService;


    @RequestMapping(value = "/resourceTypes", method = RequestMethod.GET)
    public List<ResourceTypeDTO> loadResourceTypes(){

        return DtoUtilMapper.resTypesToResTypesDTO(resTypeService.getInstances());

    }


    @RequestMapping(value = "/resourceProperties/getEssentials/{resourceTypeId}", method = RequestMethod.GET)
    public List<ResourceProperty> loadSpecResourceProperty(@PathVariable String resourceTypeId){
        
        ResourceType resourceType = resTypeService.findWithPropertiesByID(Long.parseLong(resourceTypeId));

        return resTypeService.getSearchableProperties(resourceType);
    }
    
    
    @RequestMapping(value = "/lookUpByInputValues", method = RequestMethod.POST)
    public List<GenericResource> getValuesFromForm(@RequestBody GenericResourceDTO resourceDTO){

        return resourceService.findResourcesByResourceType(resourceDTO);
    }
    
    
}
