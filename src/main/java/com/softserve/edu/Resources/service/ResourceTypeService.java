package com.softserve.edu.Resources.service;

import com.softserve.edu.Resources.dto.ResourceTypeBrief;
import com.softserve.edu.Resources.entity.ConstrainedProperty;
import com.softserve.edu.Resources.entity.ResourceType;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ResourceTypeService {

    Collection<ResourceType> getTypes();

    long getTypeCount();

    ResourceType save(ResourceTypeBrief resourceType);

    void remove(ResourceType resourceType);

    Optional<ResourceType> get(String typeName);

    void create(String typeName);

    void createBatch(List<String> typeNames);

    void create(ResourceType type);

    List<ResourceType> getInstances();

    int getInstancesCount();
    
    ResourceType findWithPropertiesByID(Long ID);

    List<ConstrainedProperty> getSearchableProperties(ResourceType resourceWithProperties);

    Optional<ResourceType> get(Long id);

    Optional<ResourceType> get(Long id, boolean fetch);
}