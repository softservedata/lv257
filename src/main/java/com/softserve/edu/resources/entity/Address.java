package com.softserve.edu.resources.entity;

/**
 * Main purpose of Address entity is to bind every {@code User}, {@code Owner}
 * and {@code Resource} to a particular location.
 */
public class Address {

  private int id;
  private String postalIndex;
  private String country;
  private String region;
  private String district;
  private String locality;
  private String street;
  private int building;
  private String block;
  private int apartment;

  public String getPostalIndex() {
    return postalIndex;
  }

  public Address setPostalIndex(String postalIndex) {
    this.postalIndex = postalIndex;
    return this;
  }

  public String getCountry() {
    return country;
  }

  public Address setCountry(String country) {
    this.country = country;
    return this;
  }

  public String getRegion() {
    return region;
  }

  public Address setRegion(String region) {
    this.region = region;
    return this;
  }

  public String getDistrict() {
    return district;
  }

  public Address setDistrict(String district) {
    this.district = district;
    return this;
  }

  public String getLocality() {
    return locality;
  }

  public Address setLocality(String locality) {
    this.locality = locality;
    return this;
  }

  public String getStreet() {
    return street;
  }

  public Address setStreet(String street) {
    this.street = street;
    return this;
  }

  public int getBuilding() {
    return building;
  }

  public Address setBuilding(int building) {
    this.building = building;
    return this;
  }

  public String getBlock() {
    return block;
  }

  public Address setBlock(String block) {
    this.block = block;
    return this;
  }

  public int getFlat() {
    return apartment;
  }

  public Address setFlat(int flat) {
    this.apartment = flat;
    return this;
  }
}
