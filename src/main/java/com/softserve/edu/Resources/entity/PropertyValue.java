package com.softserve.edu.Resources.entity;

public class PropertyValue {

  private int id;
  private ResourceProperty type;
  private String value;

  public PropertyValue() {
  }

    public PropertyValue(ResourceProperty type) {
        this.type = type;
    }

    public PropertyValue(ResourceProperty type, String value) {
    this(type);
    setValue(value);
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
                                             + type.getRegex());
    }
    this.value = value;
    return this;
  }

  private boolean validate(String value) {
    return ((type.getRegex() != null) && (value != null))
               && value.matches(type.getRegex());
  }

}
