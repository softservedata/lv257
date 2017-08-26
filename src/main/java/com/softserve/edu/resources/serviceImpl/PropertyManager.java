/*
 *
 */

package com.softserve.edu.resources.service;

import com.softserve.edu.resources.entity.ResourceProperty;

import java.util.*;
import java.util.stream.Collectors;

/*
 * @PropertyManager's is a dictionary of any kind of ResourceProperties.
 */

public class PropertyManager {

  private Map<String, ResourceProperty> properties = new HashMap<>();

  public PropertyManager() {
  }

  public boolean addProperty(ResourceProperty property) {
    int size = properties.size();
    properties.putIfAbsent(property.toString().toLowerCase(), property);
    return size < properties.size();
  }

  public Map<String, ResourceProperty> getProperties() {
    return properties;
  }

  public PropertyManager setProperties(Map<String, ResourceProperty> properties) {
    this.properties = properties;
    return this;
  }

  public Optional<ResourceProperty> getProperty(String propertyName, String unitsName) {
    return Optional.ofNullable(properties.get(String.join(", ", propertyName, unitsName)
                                                  .toLowerCase()));
  }

  public Optional<ResourceProperty> getProperty(String description) {
    return Optional.ofNullable(properties.get(description.toLowerCase()));
  }

  public List<ResourceProperty> getProperties(String propertyName) {
    return properties.values().stream()
               .filter(property -> property.getName().equalsIgnoreCase(propertyName))
               .collect(Collectors.toList());
  }

  public int propertiesCount() {
    return properties.size();
  }

  public List<String> propertyDescriptions() {
    return properties.values().stream()
               .map(ResourceProperty::getDescription)
               .sorted()
               .collect(Collectors.toList());
  }
}
