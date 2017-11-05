package com.softserve.edu.Resources.service;

import com.softserve.edu.Resources.dto.GenericResourceDTO;
import com.softserve.edu.Resources.dto.GroupedResourceCount;
import com.softserve.edu.Resources.dto.ResourceImplDTO;
import com.softserve.edu.Resources.dto.ValidationErrorDTO;
import com.softserve.edu.Resources.entity.GenericResource;
import com.softserve.edu.Resources.entity.Resource;
import com.softserve.edu.Resources.entity.ResourceType;

import java.util.List;
import java.util.Map;

public interface ResourceService {

//    Collection<GenericResource> getResources();

//    Collection<ResourceType> getInstantiatedResourceTypes();
//
//    GenericResource addResource(ResourceType resourceType, Set<PropertyValue> resourceValues);

    void addResource(Resource resource);

    void addResourceOwnings(Resource resource, ResourceImplDTO resourceImplDTO);

    void addResourceImpl(Resource resource, ResourceType resourceType, Map<String, String> propertiesAndValues);

    List<GenericResource> findResourcesByResourceType(GenericResourceDTO genericResourceDTO);
    
    List<GroupedResourceCount> findResourcesCountGroupedByResourceTypeForOwner(String ownerId);
    
    List<GenericResource> findResourcesByOwnerAndType(long ownerId, String resourceTypeName);

    ValidationErrorDTO validateResourceImpl(ResourceImplDTO resourceImplDTO);
}