package com.softserve.edu.Resources.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder

@Entity
@Table(name = "person_owner")
/**
 * Represents Person entity as a resource owner
 */
public class PersonOwner extends Owner {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "passport_series")
    private String passportSeries;

    @Column(name = "passport_number")
    private String passportNumber;

    public PersonOwner setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public PersonOwner setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public PersonOwner setMiddleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public PersonOwner setPassportSeries(String passportSeries) {
        this.passportSeries = passportSeries;
        return this;
    }

    public PersonOwner setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
        return this;
    }



    @Override
    public String toString() {
        return "PersonOwner{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", passportSeries='" + passportSeries + '\'' +
                ", passportNumber='" + passportNumber + '\'' +
                "} " + super.toString();
    }

}
