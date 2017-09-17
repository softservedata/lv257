package com.softserve.edu.Resources.service;

import com.softserve.edu.Resources.entity.ResourceType;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

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

}