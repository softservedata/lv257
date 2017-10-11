package com.softserve.edu.Resources.service;

import com.softserve.edu.Resources.dto.ResourcePropertyDescription;
import com.softserve.edu.Resources.dto.ValueTypeDTO;
import com.softserve.edu.Resources.entity.ResourceProperty;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface PropertyService {

    ResourceProperty add(ResourceProperty property);

    ResourceProperty update(ResourceProperty property);

    Set<ResourceProperty> getProperties();

    Optional<ResourceProperty> getProperty(String propertyName, String unitsName);

    Optional<ResourceProperty> getProperty(String description);

    Set<ResourceProperty> getProperties(String propertyName);

    Long propertiesCount();

    List<ResourcePropertyDescription> getPropertyDescriptions();

    List<ValueTypeDTO> getValueTypes();

    Set<Long> getPropertyIDs();

    Optional<ResourceProperty> getPropertyById(Long propID);
}