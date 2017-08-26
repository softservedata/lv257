package com.softserve.edu.resources.entity;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
 * Instances of @ResourceProperty describe particular property
 * intrinsic to a particular kind of GenericResource. GenericResource
 * can have a collection (a set in general) of different properties.
 */

public class ResourceProperty {


  private static Map<String, Class<?>> valueTypes = new HashMap<>();
  private static Map<String, String> defaultValidationRegex = new HashMap<>();

  private int id;
  private String name;
  private String units;
  private String validationRegex = ".+";
  private Class<?> valueType;
  private boolean multivalued = false;
  private String defaultValue = "";
  private boolean allowsNull = true;
  private boolean essential = false;
  private boolean secured = false;  // should metadata concern about security?
                                    // otherwise find entity to redirect that concern to

  static {
    valueTypes.put("integer", Integer.class);
    valueTypes.put("double", Double.class);
    valueTypes.put("string", String.class);
    valueTypes.put("boolean", Boolean.class);
    valueTypes.put("timestamp", Timestamp.class);
    valueTypes.put("resource property", ResourceProperty.class); // JSON representation, i.e. {"typeID": "12"}

    defaultValidationRegex.put("integer", "\\d+");
    defaultValidationRegex.put("double", "\\d+\\.\\d{3}");
    defaultValidationRegex.put("string", "\\p{L}+");
    defaultValidationRegex.put("boolean", "(yes)|(no)");
    defaultValidationRegex.put("timestamp", "");
    // JSON representation of a set of ResourceProperty, i.e. {"typeID": "12", "values": ["value1", "value2", ... ]}
    defaultValidationRegex.put("resource property", "{{},[]}");
  }

  public ResourceProperty() {
  }

  public ResourceProperty(String name) {
    this.name = name;
  }

  public static Class<?> valueClassFor(String classDescription) {
    final Class<?> aClass = valueTypes.get(classDescription);
    if (aClass == null) {
      throw new IllegalArgumentException("Inappropriate type requested");
    }
    return aClass;
  }

  public boolean isAllowsNull() {
    return allowsNull;
  }

  public ResourceProperty setAllowsNull(boolean allowsNull) {
    this.allowsNull = allowsNull;
    return this;
  }

  public String getName() {
    return name;
  }

  public ResourceProperty setName(String name) {
    this.name = name;
    return this;
  }

  public String getUnits() {
    return units;
  }

  public ResourceProperty setUnits(String units) {
    this.units = units;
    return this;
  }

  public String getValidationRegex() {
    return validationRegex;
  }

  public ResourceProperty setValidationRegex(String validationRegex) {
    this.validationRegex = validationRegex;
    return this;
  }

  public Class<?> getValueType() {
    return valueType;
  }

  public ResourceProperty setValueType(Class<?> valueType) {
    this.valueType = valueType;
    return this;
  }

  public boolean isMultivalued() {
    return multivalued;
  }

  public ResourceProperty setMultivalued(boolean multivalued) {
    this.multivalued = multivalued;
    return this;
  }

  public String getDefaultValue() {
    return defaultValue;
  }

  public ResourceProperty setDefaultValue(String defaultValue) {
    this.defaultValue = defaultValue;
    return this;
  }

  public boolean isEssential() {
    return essential;
  }

  public ResourceProperty setEssential(boolean essential) {
    this.essential = essential;
    return this;
  }

  public boolean isSecured() {
    return secured;
  }

  public ResourceProperty setSecured(boolean secured) {
    this.secured = secured;
    return this;
  }

  @Override
  public String toString() {
    return units == null ? name : String.join(", ", name, units);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ResourceProperty that = (ResourceProperty) o;

    if (!name.equals(that.name)) return false;
    return units != null ? units.equals(that.units) : that.units == null;
  }

  @Override
  public int hashCode() {
    int result = name.hashCode();
    result = 31 * result + (units != null ? units.hashCode() : 0);
    return result;
  }

  public String getDescription() {
    return units == null ? name : String.join(", ", name, units);
  }
}
