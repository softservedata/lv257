package com.softserve.edu.Resources.service;

import com.softserve.edu.Resources.dto.GenericResourceDTO;
import com.softserve.edu.Resources.dto.GroupedResourceCount;
import com.softserve.edu.Resources.entity.GenericResource;
import com.softserve.edu.Resources.entity.PropertyValue;
import com.softserve.edu.Resources.entity.ResourceType;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.web.bind.annotation.PathVariable;

public interface ResourceService {

//    Collection<GenericResource> getResources();

//    Collection<ResourceType> getInstantiatedResourceTypes();
//
//    GenericResource addResource(ResourceType resourceType, Set<PropertyValue> resourceValues);
    
    List<GenericResource> findResourcesByResourceType(GenericResourceDTO genericResourceDTO);
    
    List<GroupedResourceCount> findResourcesCountGroupedByResourceTypeForOwner(String ownerId);
    
    List<GenericResource> findResourcesByOwnerAndType(long ownerId, String resourceTypeName);
    

}