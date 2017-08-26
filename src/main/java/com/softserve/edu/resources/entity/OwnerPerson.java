package com.softserve.edu.resources.entity;

/**
 *
 */
public class OwnerPerson extends Owner {

  private String firstName;
  private String lastName;
  private String middleName;
  private String passportSeries;
  private String passportNumber;
  private Address address;
  private String phone;
  private String itn; // individual tax number

  public String getFirstName() {
    return firstName;
  }

  public OwnerPerson setFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public String getLastName() {
    return lastName;
  }

  public OwnerPerson setLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public String getMiddleName() {
    return middleName;
  }

  public OwnerPerson setMiddleName(String middleName) {
    this.middleName = middleName;
    return this;
  }

  public String getPassportSeries() {
    return passportSeries;
  }

  public OwnerPerson setPassportSeries(String passportSeries) {
    this.passportSeries = passportSeries;
    return this;
  }

  public String getPassportNumber() {
    return passportNumber;
  }

  public OwnerPerson setPassportNumber(String passportNumber) {
    this.passportNumber = passportNumber;
    return this;
  }

  public Address getAddress() {
    return address;
  }

  public OwnerPerson setAddress(Address address) {
    this.address = address;
    return this;
  }
}
