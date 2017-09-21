package com.softserve.edu.Resources.service;

import com.softserve.edu.Resources.entity.GenericResource;
import com.softserve.edu.Resources.entity.ResourceProperty;
import com.softserve.edu.Resources.entity.ResourceType;



import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ResourceTypeService {

    Collection<ResourceType> getTypes();

    long getTypeCount();

    ResourceType add(ResourceType resourceType);

    void remove(ResourceType resourceType);

    Optional<ResourceType> get(String typeName);

    void create(String typeName);

    List<ResourceType> getInstances();

    int getInstancesCount();
    
    
    
    
    ResourceType findWithPropertiesByID(Long ID);

    List<ResourceProperty> getSpecialResourcePropertiesByResType(ResourceType resourceWithProperties);
    
    void testHello();

}