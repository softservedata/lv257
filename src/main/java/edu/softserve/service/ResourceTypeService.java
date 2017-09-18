package edu.softserve.service;

import java.util.List;
import java.util.Map;

import edu.softserve.entity.GenericResource;
import edu.softserve.entity.ResourceProperty;
import edu.softserve.entity.ResourceType;

public interface ResourceTypeService {

    List <ResourceType> findAll();

    ResourceType findWithPropertiesByID(int resourceID);

    List<ResourceProperty> getSpecialResourceProperties(ResourceType resourceWithProperties);
    
    List<GenericResource> findResourcesByResourceType(String query, String tableName, Map<String, String> valuesToSearch);
    
    void testHello();
    
}
