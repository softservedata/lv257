package com.softserve.edu.resources.serviceImpl;

import com.softserve.edu.resources.entity.GenericResource;
import com.softserve.edu.resources.entity.PropertyValue;
import com.softserve.edu.resources.entity.ResourceType;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Deprecated
public class ResourceManager {

  private Collection<GenericResource> resources = new HashSet<>();
  private ResourceTypeManager typeManager = new ResourceTypeManager();

  public ResourceManager() {
  }

  public Collection<GenericResource> getResources() {
    return Collections.unmodifiableCollection(resources);
  }

  public Collection<ResourceType> getResourceTypes() {
    return resources.stream().map(GenericResource::getType).collect(Collectors.toSet());
  }

  public GenericResource addResource(ResourceType resourceType, Set<PropertyValue> resourceValues) {
    typeManager.add(resourceType);
    GenericResource resource = new GenericResource(resourceType, resourceValues);
    resources.add(resource);
    return resource;
  }
}
