package com.softserve.edu.Resources.entity;import com.softserve.edu.Resources.Constants;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "person_owner")
/**
 * Represents Person entity as a resource owner
 */
public class Person extends Owner {

    @Column(name = "first_name")
    @JsonProperty("first name")
    private String firstName;

    @Column(name = "last_name")
    @JsonProperty("last name")
    private String lastName;

    @Column(name = "middle_name")
    @JsonProperty("middle name")
    private String middleName;

    @Column(name = "passport_series")
    @JsonProperty("passport series")
    private String passportSeries;

    @Column(name = "passport_number")
    @JsonProperty("passport number")
    private String passportNumber;

    public Person() {
    }

    public Person setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public Person setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Person setMiddleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public Person setPassportSeries(String passportSeries) {
        this.passportSeries = passportSeries;
        return this;
    }

    public Person setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getPassportSeries() {
        return passportSeries;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        if (!super.equals(o)) return false;

        Person person = (Person) o;

        if (firstName != null ? !firstName.equals(person.firstName) : person.firstName != null) return false;
        if (lastName != null ? !lastName.equals(person.lastName) : person.lastName != null) return false;
        if (middleName != null ? !middleName.equals(person.middleName) : person.middleName != null) return false;
        if (passportSeries != null ? !passportSeries.equals(person.passportSeries) : person.passportSeries != null)
            return false;
        return passportNumber != null ? passportNumber.equals(person.passportNumber) : person.passportNumber == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (middleName != null ? middleName.hashCode() : 0);
        result = 31 * result + (passportSeries != null ? passportSeries.hashCode() : 0);
        result = 31 * result + (passportNumber != null ? passportNumber.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", passportSeries='" + passportSeries + '\'' +
                ", passportNumber='" + passportNumber + '\'' +
                "} " + super.toString();
    }

}
