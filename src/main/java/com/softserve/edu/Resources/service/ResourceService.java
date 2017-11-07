package com.softserve.edu.Resources.service;

import com.softserve.edu.Resources.dto.GenericResourceDTO;
import com.softserve.edu.Resources.dto.GroupedResourceCount;
import com.softserve.edu.Resources.dto.ResourceImplDTO;
import com.softserve.edu.Resources.dto.ValidationErrorDTO;
import com.softserve.edu.Resources.entity.GenericResource;
import com.softserve.edu.Resources.entity.Resource;

import java.util.List;

public interface ResourceService {

    void addResource(Resource resource, ResourceImplDTO resourceImplDTO);

    List<GenericResource> findResourcesByResourceType(GenericResourceDTO genericResourceDTO);
    
    List<GroupedResourceCount> findResourcesCountGroupedByResourceTypeForOwner(String ownerId);
    
    List<GenericResource> findResourcesByOwnerAndType(long ownerId, String resourceTypeName);
    
    GenericResourceDTO findResourceByTypeAndId(long resourceTypeId, long resourceId);

    ValidationErrorDTO validateResourceImpl(ResourceImplDTO resourceImplDTO);

    ValidationErrorDTO validateResourceImplUniqueFields(ResourceImplDTO resourceImplDTO);
}
