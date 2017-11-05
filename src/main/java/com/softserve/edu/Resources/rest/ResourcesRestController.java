package com.softserve.edu.Resources.rest;

import com.softserve.edu.Resources.dto.ExceptionJSONInfo;
import com.softserve.edu.Resources.dto.ResourceTypeBrief;
import com.softserve.edu.Resources.dto.TypeInfoDTO;
import com.softserve.edu.Resources.dto.ViewTypesDTO;
import com.softserve.edu.Resources.entity.ResourceType;
import com.softserve.edu.Resources.exception.CycleDependencyException;
import com.softserve.edu.Resources.exception.ResourceTypeInstantiationException;
import com.softserve.edu.Resources.exception.ResourceTypeNotFoundException;
import com.softserve.edu.Resources.service.ResourceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api", method = RequestMethod.GET)
public class ResourcesRestController {

    @Autowired
    ResourceTypeService resourceTypeService;

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
    public List<ViewTypesDTO> getAllResourceTypes() {
        return resourceTypeService.getTypes().stream()
                .map(ViewTypesDTO::new)
                .sorted(Comparator.comparing(ViewTypesDTO::getTypeName))
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/typeInfo/{id}", method = RequestMethod.GET)
    public TypeInfoDTO getResourceTypeInfo(@PathVariable Long id) {
        Optional<ResourceType> resourceType = resourceTypeService.get(id, true);
        if (!resourceType.isPresent())
            throw new ResourceTypeNotFoundException("Requested Resource Type not found.");
        return new TypeInfoDTO(resourceType.get());
    }

    @RequestMapping(value = "/deleteType/{id}", method = RequestMethod.DELETE)
    public void deleteResourceType(@PathVariable Long id) {
        resourceTypeService.removeById(id);
    }

    @RequestMapping(value = "/instantiateType/{id}", method = RequestMethod.PUT)
    public void instantiateResourceType(@PathVariable Long id) {
        resourceTypeService.create(id);
    }

    @ExceptionHandler(ResourceTypeInstantiationException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public ExceptionJSONInfo handleResourceTypeInstantiationException(HttpServletRequest request, Exception ex) {
        ExceptionJSONInfo response = new ExceptionJSONInfo();
        response.setUrl(request.getRequestURL().toString());
        response.setMessage(ex.getMessage());
        return response;
    }
}
