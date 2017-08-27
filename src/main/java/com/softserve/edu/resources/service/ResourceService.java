package com.softserve.edu.resources.service;

import java.util.Collection;
import java.util.Set;

import com.softserve.edu.resources.entity.GenericResource;
import com.softserve.edu.resources.entity.PropertyValue;
import com.softserve.edu.resources.entity.ResourceType;

public interface ResourceService {

    Collection<GenericResource> getResources();

    Collection<ResourceType> getResourceTypes();

    GenericResource addResource(ResourceType resourceType,
            Set<PropertyValue> resourceValues);

}