package com.softserve.edu.Resources.dao;

import java.util.List;
import java.util.Map;

import com.softserve.edu.Resources.entity.GenericResource;
import com.softserve.edu.Resources.entity.ResourceProperty;

public interface ResourceDao {
    
    List<GenericResource> findResourcesByResourceType(String sqlQuery, Map<String, String> valuesToSearh,
            List<ResourceProperty> resourceProperties);

}
