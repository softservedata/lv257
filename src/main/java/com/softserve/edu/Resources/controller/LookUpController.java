package com.softserve.edu.Resources.controller;

import com.softserve.edu.Resources.dto.DtoUtilMapper;
import com.softserve.edu.Resources.dto.GenericResourceDTO;
import com.softserve.edu.Resources.dto.ResourceTypeDTO;
import com.softserve.edu.Resources.entity.GenericResource;
import com.softserve.edu.Resources.entity.ResourceProperty;
import com.softserve.edu.Resources.entity.ResourceType;
import com.softserve.edu.Resources.service.QueryBuilderService;
import com.softserve.edu.Resources.service.ResourceService;
import com.softserve.edu.Resources.service.ResourceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;



@RestController
public class LookUpController {
    
    @Autowired
    ResourceTypeService resTypeService;
    
    @Autowired
    ResourceService resourceService;
    
    @Autowired
    QueryBuilderService queryBuilderService;

    @RequestMapping(value = "/resourceTypes", method = RequestMethod.GET)
    public List<ResourceTypeDTO> loadResourceTypes(){
        
        List <ResourceTypeDTO> resTypeDTOs = DtoUtilMapper.resTypesToResTypesDTO(resTypeService.getInstances());
        return resTypeDTOs;
        
    }
    
    
    @RequestMapping(value = "/resourceProperties/getEssentials", method = RequestMethod.POST)
    public List<ResourceProperty> loadSpecResourceProperty(@RequestBody String resourceTypeId){
        
        ResourceType resourceType = resTypeService.findWithPropertiesByID(Long.parseLong(resourceTypeId));
        
        List <ResourceProperty> specialResourceProperties = resTypeService.getSpecialResourcePropertiesByResType(resourceType);
        
        return specialResourceProperties;
    }
    
    
    @RequestMapping(value = "/lookUpByInputValues", method = RequestMethod.POST)
    public List<GenericResource> getValuesFromForm(@RequestBody GenericResourceDTO resourceDTO){
        
        String tableName = resourceDTO.getResourceTableName();
        Map <String, String> valuesToSearch = resourceDTO.getResourcePropertyValue();
        
        String queryForDao = queryBuilderService.lookUpByResouceType(tableName, valuesToSearch);
        
        List <GenericResource> genericResources = resourceService.findResourcesByResourceType(queryForDao, tableName, valuesToSearch);
        
        return genericResources;
    }
    
    
}
