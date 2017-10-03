package com.softserve.edu.Resources.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.softserve.edu.Resources.Constants;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
/**
 * Main purpose of Address entity is to bind every {@code User}, {@code Owner}
 * and {@code Resource} to a particular location.
 */
public class Address {

    @Id()
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    @JsonProperty("addressId")
    private long id;

    @NotEmpty
    @Length(max = 10, message = "Country name is too big.")
    @JsonProperty("country")
    private String country;

    @NotEmpty
    @Size(max = 7)
    @JsonProperty("region")
    private String region;

    @JsonProperty("district")
    @NotEmpty
    private String district;

    @JsonProperty("postal_index")
    @Column(name = "postal_index")
    @NotEmpty
    private String postalIndex;

    @NotEmpty
    @JsonProperty("locality")
    private String locality;

    @NotEmpty
    @JsonProperty("street")
    private String street;

    @Min(1)
    @JsonProperty("building")
    private int building;

    @JsonProperty("block")
    private String block;

    @Min(1)
    @JsonProperty("apartment")
    private int apartment;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    private Owner owner;

    public Address() {
    }

    public long getId() {
        return id;
    }

    public Address setId(long id) {
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

    public Owner getOwner() {
        return owner;
    }

    public Address setOwner(Owner owner) {
        this.owner = owner;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;

        Address address = (Address) o;

        if (id != address.id) return false;
        if (building != address.building) return false;
        if (apartment != address.apartment) return false;
        if (!country.equals(address.country)) return false;
        if (!region.equals(address.region)) return false;
        if (district != null ? !district.equals(address.district) : address.district != null) return false;
        if (!postalIndex.equals(address.postalIndex)) return false;
        if (!locality.equals(address.locality)) return false;
        if (!street.equals(address.street)) return false;
        if (block != null ? !block.equals(address.block) : address.block != null) return false;
        return owner != null ? owner.equals(address.owner) : address.owner == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + country.hashCode();
        result = 31 * result + region.hashCode();
        result = 31 * result + (district != null ? district.hashCode() : 0);
        result = 31 * result + postalIndex.hashCode();
        result = 31 * result + locality.hashCode();
        result = 31 * result + street.hashCode();
        result = 31 * result + building;
        result = 31 * result + (block != null ? block.hashCode() : 0);
        result = 31 * result + apartment;
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
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

    public String customToString(){
        return  country + ", " +
                region + ", " +
                district + ", " +
                postalIndex + ", " +
                locality + ", " +
                street + ", " +
                building + (block.isEmpty()? ", " : " " + block + " " ) +
                apartment + ".";
    }
}

