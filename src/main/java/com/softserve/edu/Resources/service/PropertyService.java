package com.softserve.edu.Resources.service;

import com.softserve.edu.Resources.entity.ResourceProperty;

import java.util.List;
import java.util.Optional;

public interface PropertyService {

    ResourceProperty add(ResourceProperty property);

    ResourceProperty update(ResourceProperty property);

    List<ResourceProperty> getProperties();

    Optional<ResourceProperty> getProperty(String propertyName, String unitsName);

    Optional<ResourceProperty> getProperty(String description);

    List<ResourceProperty> getProperties(String propertyName);

    int propertiesCount();

    List<String> getPropertyDescriptions();

}