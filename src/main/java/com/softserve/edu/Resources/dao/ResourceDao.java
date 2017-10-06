package com.softserve.edu.Resources.dao;

import com.softserve.edu.Resources.entity.ConstrainedProperty;
import com.softserve.edu.Resources.entity.GenericResource;

import java.util.List;
import java.util.Map;

public interface ResourceDao {
    
    List<GenericResource> findResourcesByResourceType(String sqlQuery, Map<String, String> valuesToSearh,
            List<ConstrainedProperty> resourceProperties);

}
