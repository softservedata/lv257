package com.softserve.edu.resources.entity;

public class PropertyValue {

  private int id;
  private ResourceProperty type;
  private String value;

  public PropertyValue() {
  }

  public PropertyValue(ResourceProperty type, String value) {
    this.type = type;
    this.value = value;
  }

  public ResourceProperty getType() {
    return type;
  }

  public PropertyValue setType(ResourceProperty type) {
    this.type = type;
    return this;
  }

  public String getValue() {
    return value;
  }

  public PropertyValue setValue(String value) {
    if (!type.isAllowsNull() && value == null) {
      throw new NullPointerException("NOT NULL constraint violation.");
    }
    if (!validate(value)) {
      throw new IllegalArgumentException("Invalid value format. Should match "
                                             + type.getValidationRegex());
    }
    this.value = value;
    return this;
  }

  private boolean validate(String value) {
    return ((type.getValidationRegex() != null) && (value != null))
               && value.matches(type.getValidationRegex());
  }

}
