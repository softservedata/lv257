package com.softserve.edu.Resources.entity;

public class PropertyValue implements Comparable<PropertyValue> {

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
        System.out.println(value);
      throw new IllegalArgumentException("Invalid value format. Should match "
                                             + type.getRegex());
    }
    this.value = value;
    return this;
  }

  private boolean validate(String value) {
      System.out.println(type.getRegex());
    return ((type.getRegex() != null) && (value != null))
               && value.matches(type.getRegex());
  }

    @Override
    public String toString() {
        return "PropertyValue [type=" + type + ", value=" + value + "]";
    }

    @Override
    public int compareTo(PropertyValue o) {
        
        return this.getType().getColumnName().compareTo(o.getType().getColumnName());
    }

  
  
}
