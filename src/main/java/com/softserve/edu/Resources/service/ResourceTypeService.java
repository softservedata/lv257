package com.softserve.edu.Resources.service;

import com.softserve.edu.Resources.dto.ResourceTypeBrief;
import com.softserve.edu.Resources.entity.ConstrainedProperty;
import com.softserve.edu.Resources.entity.ResourceType;
import com.softserve.edu.Resources.entity.User;
import com.softserve.edu.Resources.exception.ResourceTypeInstantiationException;
import com.softserve.edu.Resources.exception.ResourceTypeNotFoundException;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ResourceTypeService {

    Collection<ResourceType> getTypes();

    long getTypeCount();

    ResourceType save(ResourceTypeBrief resourceType, User resourceAdmin);

    void remove(ResourceType resourceType);

    void removeById(Long id) throws ResourceTypeNotFoundException, ResourceTypeInstantiationException;

    Optional<ResourceType> get(String typeName);

    void instantiateType(Long id) throws ResourceTypeNotFoundException, ResourceTypeInstantiationException;

    void createBatch(List<Long> IDs);

    List<ResourceType> getInstances();

    int getInstancesCount();
    
    ResourceType findWithPropertiesByID(Long ID);

    List<ConstrainedProperty> getSearchableProperties(ResourceType resourceWithProperties);

    Optional<ResourceType> get(Long id);

    Optional<ResourceType> get(Long id, boolean fetch);
}