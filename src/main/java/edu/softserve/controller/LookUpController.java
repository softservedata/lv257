package edu.softserve.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.softserve.dto.DtoUtilMapper;
import edu.softserve.dto.GenericResourceDTO;
import edu.softserve.dto.ResourceTypeDTO;
import edu.softserve.entity.GenericResource;
import edu.softserve.entity.ResourceProperty;
import edu.softserve.entity.ResourceType;
import edu.softserve.service.ResourceTypeService;
import edu.softserve.service.UtilQueryBuilderService;

@RestController
public class LookUpController {
    
    @Autowired
    ResourceTypeService resTypeService;
    
    @Autowired
    UtilQueryBuilderService utilQuerybuilderService;

    @RequestMapping(value = "/loadResourceTypes", method = RequestMethod.GET)
//    @GetMapping("/loadResourceTypes")
    public List<ResourceTypeDTO> loadResourceTypes(){
        
        List <ResourceTypeDTO> resTypeDTOs = DtoUtilMapper.resTypesToResTypesDTO(resTypeService.findAll());
        return resTypeDTOs;
        
    }
    
    /*ASK/todo: 1) how to properly write javascript, jsp page code(view), 
                   to show/build form/table/etc from list of resource properties
                   (which was returned)
                   dynamically depending on chosen resource type
                   what to read, examples??   */
    @RequestMapping(value = "/loadSpecResourceProperties", method = RequestMethod.GET)
    public List<ResourceProperty> loadSpecResourceProperty(@RequestBody String resourceTypeId){
        
        ResourceType resourceType = resTypeService.findWithPropertiesByID(Integer.parseInt(resourceTypeId));
        
        List <ResourceProperty> specialResourceProperties = resTypeService.getSpecialResourceProperties(resourceType);
        
        return specialResourceProperties;
    }
    
    
    /* ASK: 1) how to properly write javascript, ajax, jsp to get values of properties
     *         (which was filled in by client)
     *         from dynamic form/table/etc in controller method
     *   what to read???
    */
    @RequestMapping(value = "/getValuesFromForm", method = RequestMethod.GET)
    public List<GenericResource> getValuesFromForm(@RequestBody GenericResourceDTO resourceDTO){
        
        String tableName = resourceDTO.getResourceTableName();
        Map <String, String> valuesToSearch = resourceDTO.getResourcePropertyValue();
        
        String queryForDao = utilQuerybuilderService.queryForLookUpByResouceType(tableName, valuesToSearch);
        
        List <GenericResource> genericResources = resTypeService.findResourcesByResourceType(queryForDao, tableName, valuesToSearch);
        
        
        return genericResources;
    }
    
    
}
