package com.softserve.edu.resources.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.softserve.edu.resources.dto.ResourceTypeDTO;
import com.softserve.edu.resources.entity.ResourceType;

public interface ResourceTypeService {

    Collection<ResourceType> getTypes();

    int getTypeCount();

    void add(ResourceType resourceType);

    void remove(ResourceType resourceType);

    Optional<ResourceType> get(String typeName);

    void create(String typeName);

    ResourceTypeService setTypes(Map<String, ResourceType> types);

    Map<ResourceType, String> getInstances();

    ResourceTypeService setInstances(Map<ResourceType, String> instances);

    int getInstancesCount();

    List<ResourceType> getAllResourceTypesAndParents();

    List<ResourceType> getAllResourceTypes();

    List<ResourceType> getChildrensOfResourceType(ResourceType resourceType);

    List<ResourceTypeDTO> buildTypeHierarchy();

}