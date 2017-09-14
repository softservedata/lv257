package com.softserve.edu.Resources.entity;


import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder

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
    private List<Owner> owners = new ArrayList<>();

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

