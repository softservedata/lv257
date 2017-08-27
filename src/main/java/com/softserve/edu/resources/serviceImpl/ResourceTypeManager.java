
package com.softserve.edu.resources.serviceImpl;

import com.softserve.edu.resources.entity.ResourceType;

import java.util.*;

@Deprecated
public class ResourceTypeManager {

  private Map<String, ResourceType> types = new HashMap<>();

  public Collection<ResourceType> getTypes() {
    return Collections.unmodifiableCollection(types.values());
  }

  public void add(ResourceType resourceType) {
    types.putIfAbsent(resourceType.getName(), resourceType);
  }

  public void remove(ResourceType resourceType) {
    types.remove(resourceType.getName());
  }

  public Optional<ResourceType> get(String typeName) {
    return Optional.ofNullable(types.get(typeName));
  }

  public void create(String typeName) {
    ResourceType type = types.get(typeName);
    if (type != null) {
      System.out.printf("Creating ResourceTable '%s':%n", type.getName());
      type.getProperties().forEach(rp -> System.out.printf("adding field '%s'%n", rp.getName()));
      System.out.println("done.");
    }
  }

}
