package com.softserve.edu.resources.entity;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/*
*  ResourceType implements entity containing a set of
*  ResourceProperty that describe particular GenericResource
*
* */
public class ResourceType {

  private int id;
  private String typeName;
  private String tableName;
  private ResourceType parentType;
  private Set<ResourceProperty> properties;


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ResourceType that = (ResourceType) o;

    return typeName.equals(that.typeName);
  }

  @Override
  public int hashCode() {
    return typeName.hashCode();
  }

  public ResourceType(String typeName) {
    this.typeName = typeName;
    properties = new HashSet<>();
  }

  public String getName() {
    return typeName;
  }

  public Set<ResourceProperty> getProperties() {
    return Collections.unmodifiableSet(properties);
  }

  public boolean addProperty(ResourceProperty resourceProperty) {
    return properties.add(resourceProperty);
  }

  public boolean removeProperty(ResourceProperty resourceProperty) {
    return properties.remove(resourceProperty);
  }

  public Optional<ResourceProperty> getProperty(String propertyName) {
    return properties.stream()
               .filter(rp -> rp.getName().equalsIgnoreCase(propertyName))
               .findFirst();
  }

  public boolean isInstanceType() {
    return !properties.isEmpty();
  }


}
