package com.softserve.edu.Resources.service;

import com.softserve.edu.Resources.dto.GenericResourceDTO;
import com.softserve.edu.Resources.entity.GenericResource;
import com.softserve.edu.Resources.entity.PropertyValue;
import com.softserve.edu.Resources.entity.ResourceType;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ResourceService {

//    Collection<GenericResource> getResources();

//    Collection<ResourceType> getResourceTypes();
//
//    GenericResource addResource(ResourceType resourceType, Set<PropertyValue> resourceValues);
    
    List<GenericResource> findResourcesByResourceType(GenericResourceDTO genericResourceDTO);

}