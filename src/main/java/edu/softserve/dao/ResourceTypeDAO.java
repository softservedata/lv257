package edu.softserve.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import edu.softserve.entity.GenericResource;
import edu.softserve.entity.ResourceProperty;
import edu.softserve.entity.ResourceType;

public interface ResourceTypeDAO {

    List <ResourceType> findAll();

    ResourceType findWithPropertiesByID(int resourceTypeID);
    
    List <GenericResource> findResourcesByResourceType(String sqlQuery, 
            Map <String,String> valuesToSearh, List <ResourceProperty> resourceProperties);
    
    ResourceType findWithPropertiesByTableName(String tableName);
    
}
