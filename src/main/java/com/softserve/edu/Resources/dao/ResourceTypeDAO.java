package com.softserve.edu.Resources.dao;

import com.softserve.edu.Resources.entity.GenericResource;
import com.softserve.edu.Resources.entity.ResourceProperty;
import com.softserve.edu.Resources.entity.ResourceType;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ResourceTypeDAO extends GenericDAO<ResourceType, Long> {

    Optional<ResourceType> findByName(String name);

    List<ResourceType> findByProperty(ResourceProperty property);

    List<String> getInstanceNames();

    List<ResourceType> getInstances();
    
    

    ResourceType findWithPropertiesByID(Long resourceTypeID);

    ResourceType findWithPropertiesByTableName(String tableName);

}
