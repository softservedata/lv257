package com.softserve.edu.resources.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.softserve.edu.resources.entity.ResourceProperty;

public interface PropertyService {

    boolean addProperty(ResourceProperty property);

    Map<String, ResourceProperty> getProperties();

    PropertyService setProperties(Map<String, ResourceProperty> properties);

    Optional<ResourceProperty> getProperty(String propertyName,
            String unitsName);

    Optional<ResourceProperty> getProperty(String description);

    List<ResourceProperty> getProperties(String propertyName);

    int propertiesCount();

    List<String> propertyDescriptions();

}