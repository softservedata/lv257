package com.softserve.edu.resources.entity;

import java.util.Collections;
import java.util.Set;

/**
 * Common abstraction to all kind(particularly only two) of owners.
 */
public abstract class Owner {

  private int id;
  private Set<ResourceType> propertyTypes;
  private Address addr;
  private String phone;

  public Set<ResourceType> getPropertyTypes() {
    return Collections.unmodifiableSet(propertyTypes);
  }

  public Owner setPropertyTypes(Set<ResourceType> propertyTypes) {
    this.propertyTypes = propertyTypes;
    return this;
  }

  public Address getAddr() {
    return addr;
  }

  public Owner setAddr(Address addr) {
    this.addr = addr;
    return this;
  }

  public String getPhone() {
    return phone;
  }

  public Owner setPhone(String phone) {
    this.phone = phone;
    return this;
  }
}
