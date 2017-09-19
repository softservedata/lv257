package com.softserve.edu.Resources.entity;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

public interface Resource {

  ResourceType getType();
  Optional<PropertyValue> getValue(ResourceProperty resourceProperty);
  void setValue(ResourceProperty resourceProperty, PropertyValue value);
  Collection<PropertyValue> getValues();
  void setValues(Set<PropertyValue> values);
}
