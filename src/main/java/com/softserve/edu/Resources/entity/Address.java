package com.softserve.edu.Resources.entity;

import javax.persistence.*;
import java.util.List;

@Entity
/**
 * Main purpose of Address entity is to bind every {@code User}, {@code Owner}
 * and {@code Resource} to a particular location.
 */
public class Address {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String country;
    private String region;
    private String district;

    @Column(name = "postal_index")
    private String postalIndex;
    private String locality;
    private String street;
    private int building;
    private String block;
    private int apartment;

    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Owner> owners;

    public Address() {
    }

    public int getId() {
        return id;
    }

    public Address setId(int id) {
        this.id = id;
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

    public String getPostalIndex() {
        return postalIndex;
    }

    public Address setPostalIndex(String postalIndex) {
        this.postalIndex = postalIndex;
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

    public int getApartment() {
        return apartment;
    }

    public Address setApartment(int apartment) {
        this.apartment = apartment;
        return this;
    }

    public List<Owner> getOwners() {
        return owners;
    }

    public Address setOwners(List<Owner> owners) {
        this.owners = owners;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        if (id != address.id) return false;
        if (building != address.building) return false;
        if (apartment != address.apartment) return false;
        if (!country.equals(address.country)) return false;
        if (!region.equals(address.region)) return false;
        if (!district.equals(address.district)) return false;
        if (!postalIndex.equals(address.postalIndex)) return false;
        if (!locality.equals(address.locality)) return false;
        if (!street.equals(address.street)) return false;
        if (!block.equals(address.block)) return false;
        return owners.equals(address.owners);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + country.hashCode();
        result = 31 * result + region.hashCode();
        result = 31 * result + district.hashCode();
        result = 31 * result + postalIndex.hashCode();
        result = 31 * result + locality.hashCode();
        result = 31 * result + street.hashCode();
        result = 31 * result + building;
        result = 31 * result + block.hashCode();
        result = 31 * result + apartment;
        result = 31 * result + owners.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", region='" + region + '\'' +
                ", district='" + district + '\'' +
                ", postalIndex='" + postalIndex + '\'' +
                ", locality='" + locality + '\'' +
                ", street='" + street + '\'' +
                ", building=" + building +
                ", block='" + block + '\'' +
                ", apartment=" + apartment +
                '}';
    }
}

