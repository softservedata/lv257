package com.softserve.edu.Resources.entity;

import java.util.*;
import java.util.stream.Collectors;


public class GenericResource implements Resource {

  private int id;
//  private Set<Owner> owners;
  private ResourceType type;
  private Map<ResourceProperty, PropertyValue> resourceValues;


  public GenericResource() {
  }

  /**
   * Create instance implementing {@code Resource} described with {@code resourceValues}.
   *
   * @param resourceType type of particular Resource
   * @param resourceValues {@code Set} of {@code PropertyValue}s describing resource
   */
  public GenericResource(ResourceType resourceType, Set<PropertyValue> resourceValues) {
    type = resourceType;
    this.resourceValues = resourceValues.stream()
                              .collect(Collectors.toMap(PropertyValue::getType, value -> value));
  }

  public int getId() {
    return id;
  }

  public GenericResource setId(int id) {
    this.id = id;
    return this;
  }

  /*public GenericResource setOwners(Set<Owner> owners) {
    this.owners = owners;
    return this;
  }*/

  public GenericResource setType(ResourceType type) {
    this.type = type;
    return this;
  }

  public Map<ResourceProperty, PropertyValue> getResourceValues() {
    return resourceValues;
  }

  public ResourceType getType() {
    return type;
  }

/*  @Override
  public Set<Owner> getOwners() {
    return Collections.unmodifiableSet(owners);
  }*/

  public GenericResource setResourceValues(Map<ResourceProperty, PropertyValue> resourceValues) {
    this.resourceValues = resourceValues;
    return this;
  }

  @Override
  public Optional<PropertyValue> getValue(ResourceProperty resourceType) {
    return Optional.ofNullable(resourceValues.get(resourceType));
  }

  @Override
  public void setValue(ResourceProperty resourceProperty, PropertyValue value) {
    resourceValues.put(resourceProperty, value);
  }

  @Override
  public Collection<PropertyValue> getValues() {
    return Collections.unmodifiableCollection(resourceValues.values());
  }

  @Override
  public void setValues(Set<PropertyValue> values) {
    values.forEach(value -> resourceValues.put(value.getType(), value));
  }

}
