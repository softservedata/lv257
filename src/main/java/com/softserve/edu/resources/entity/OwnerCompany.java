package com.softserve.edu.resources.entity;

/**
 * Represents Company entity as a resource owner
 */
public class OwnerCompany extends Owner {

  private String fullName;
  private String shortName;
  private String CEO;

  public String getFullName() {
    return fullName;
  }

  public OwnerCompany setFullName(String fullName) {
    this.fullName = fullName;
    return this;
  }

  public String getShortName() {
    return shortName;
  }

  public OwnerCompany setShortName(String shortName) {
    this.shortName = shortName;
    return this;
  }

  public String getCEO() {
    return CEO;
  }

  public OwnerCompany setCEO(String CEO) {
    this.CEO = CEO;
    return this;
  }
}
