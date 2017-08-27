package com.softserve.edu.resources.serviceImpl;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.softserve.edu.resources.entity.GenericResource;
import com.softserve.edu.resources.entity.PropertyValue;
import com.softserve.edu.resources.entity.ResourceType;
import com.softserve.edu.resources.service.ResourceService;
import com.softserve.edu.resources.service.ResourceTypeService;

@Deprecated
public class ResourceServiceImpl implements ResourceService {

  private Collection<GenericResource> resources = new HashSet<>();
  private ResourceTypeService typeService = new ResourceTypeServiceImpl();

  public ResourceServiceImpl() {
  }

  @Override
public Collection<GenericResource> getResources() {
    return Collections.unmodifiableCollection(resources);
  }

  @Override
public Collection<ResourceType> getResourceTypes() {
    return resources.stream().map(GenericResource::getType).collect(Collectors.toSet());
  }

  @Override
public GenericResource addResource(ResourceType resourceType, Set<PropertyValue> resourceValues) {
    typeService.add(resourceType);
    GenericResource resource = new GenericResource(resourceType, resourceValues);
    resources.add(resource);
    return resource;
  }
}
