package com.softserve.edu.Resources.dao;

import com.softserve.edu.Resources.dto.GroupedResourceCount;
import com.softserve.edu.Resources.entity.ConstrainedProperty;
import com.softserve.edu.Resources.entity.GenericResource;
import com.softserve.edu.Resources.entity.Resource;
import com.softserve.edu.Resources.entity.ResourceOwning;

import java.util.List;
import java.util.Map;

public interface ResourceDao {

    void addResource(Resource resource);

    void addResourceOwning(ResourceOwning resourceOwning);

    public void addResourceImpl(String query);

    List<GenericResource> findResourcesByResourceType(String sqlQuery, Map<String, String> valuesToSearh,
            List<ConstrainedProperty> resourceProperties);

    List<GroupedResourceCount> findResourcesCountGroupedByResourceTypeForOwner(Long ownerId);

    List<Long> findResourcesIdsByOwner(long ownerId, String resourceTypeName);

    List<GenericResource> findResourcesByOwnerAndResourcesType(String sqlQuery,
            List<ConstrainedProperty> resourcesProperties, List<Long> resourcesIds);

}
