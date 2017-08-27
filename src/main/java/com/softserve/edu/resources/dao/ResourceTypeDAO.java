package com.softserve.edu.resources.dao;

import java.util.List;

import com.softserve.edu.resources.entity.ResourceType;

public interface ResourceTypeDAO {

    List<ResourceType> getAllResourceTypes();

    List<ResourceType> getAllResourceTypesAndParents();

    List<ResourceType> getChildrensOfResourceType(ResourceType resourceType);

}
