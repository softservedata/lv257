package com.softserve.edu.Resources.controller;

import com.amazonaws.services.sns.model.NotFoundException;
import com.softserve.edu.Resources.dto.DtoUtilMapper;
import com.softserve.edu.Resources.dto.ExceptionJSONInfo;
import com.softserve.edu.Resources.dto.GenericResourceDTO;
import com.softserve.edu.Resources.dto.ResourceTypeDTO;
import com.softserve.edu.Resources.entity.ConstrainedProperty;
import com.softserve.edu.Resources.entity.GenericResource;
import com.softserve.edu.Resources.entity.ResourceProperty;
import com.softserve.edu.Resources.entity.ResourceType;
import com.softserve.edu.Resources.exception.ResourceNotFoundException;
import com.softserve.edu.Resources.service.ResourceService;
import com.softserve.edu.Resources.service.ResourceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class LookUpController {

    @Autowired
    ResourceTypeService resourceTypeService;

    @Autowired
    ResourceService resourceService;

    // @RequestMapping(value = "/lookup/resourceTypes", method =
    // RequestMethod.GET)
    // public List<ResourceTypeDTO> loadResourceTypes(){
    //
    //
    // return
    // DtoUtilMapper.resTypesToResTypesDTO(resourceTypeService.getInstances());
    //
    // }

    @RequestMapping(value = "/lookUp/resourceProperties/{resourceTypeId}", method = RequestMethod.GET)

    public List<ResourceProperty> loadSpecResourceProperty(@PathVariable String resourceTypeId) {

        ResourceType resourceType = resourceTypeService.findWithPropertiesByID(Long.parseLong(resourceTypeId));

        if (resourceType == null) {
            throw new ResourceNotFoundException("No infromation was found by your request");
        }

        List<ConstrainedProperty> constraintProperties = resourceTypeService.getSearchableProperties(resourceType);

        List<ResourceProperty> resourceProperties = new ArrayList<>();

        for (ConstrainedProperty constraint : constraintProperties) {
            resourceProperties.add(constraint.getProperty());
        }

        if (resourceProperties.isEmpty()) {
            throw new ResourceNotFoundException("No infromation was found by your request");
        }

        return resourceProperties;

    }

    @RequestMapping(value = "/lookUp/inputValues", method = RequestMethod.POST)
    public List<GenericResource> getValuesFromForm(@RequestBody GenericResourceDTO resourceDTO) {

        List <GenericResource> genResList = resourceService.findResourcesByResourceType(resourceDTO);
        
        if (genResList.isEmpty()){
            throw new ResourceNotFoundException("No infromation was found by your request");
        }
        
        return genResList;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionJSONInfo noInfoFound(ResourceNotFoundException e) {
        String message = e.getMessage();
        ExceptionJSONInfo exceptionJson = new ExceptionJSONInfo();
        exceptionJson.setMessage(message);
        return exceptionJson;
    }

}
