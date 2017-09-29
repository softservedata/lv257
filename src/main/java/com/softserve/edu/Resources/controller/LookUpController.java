package com.softserve.edu.Resources.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.softserve.edu.Resources.dto.DtoUtilMapper;
import com.softserve.edu.Resources.dto.GenericResourceDTO;
import com.softserve.edu.Resources.dto.ResourceTypeDTO;
import com.softserve.edu.Resources.entity.GenericResource;
import com.softserve.edu.Resources.entity.ResourceProperty;
import com.softserve.edu.Resources.entity.ResourceType;
import com.softserve.edu.Resources.service.ResourceService;
import com.softserve.edu.Resources.service.ResourceTypeService;




@RestController
public class LookUpController {
    
    @Autowired
    ResourceTypeService resourceTypeService;
    
    @Autowired
    ResourceService resourceService;
    

    @RequestMapping(value = "/lookUpByResourceType/resourceTypes", method = RequestMethod.GET)
    public List<ResourceTypeDTO> loadResourceTypes(){
        
        List <ResourceTypeDTO> resTypeDTOs = DtoUtilMapper.resTypesToResTypesDTO(resourceTypeService.getInstances());
        return resTypeDTOs;
        
    }
    
    
    @RequestMapping(value = "/lookUpByResourceType/resourceProperties/getEssentials/{resourceTypeId}", method = RequestMethod.GET)
    public List<ResourceProperty> loadSpecResourceProperty(@PathVariable String resourceTypeId){
        
        ResourceType resourceType = resourceTypeService.findWithPropertiesByID(Long.parseLong(resourceTypeId));
        
        List <ResourceProperty> specialResourceProperties = resourceTypeService.getSpecialResourcePropertiesByResType(resourceType);
        
        return specialResourceProperties;
    }
    
    
    @RequestMapping(value = "/lookUpByResourceType/inputValues", method = RequestMethod.POST)
    public List<GenericResource> getValuesFromForm(@RequestBody GenericResourceDTO resourceDTO){
        
        List <GenericResource> genericResources = resourceService.findResourcesByResourceType(resourceDTO);
        
        return genericResources;
    }
    
    
}
