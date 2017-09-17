package com.softserve.edu.Resources.entity;

import javax.persistence.*;
import java.net.URL;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents legal document issued by authorities
 */

@Entity
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "series")
    private String series;

    @Column(name = "number")
    private String number;

    @Transient
    private Set<URL> copies = new HashSet<>();


    public Document() {
    }

    public long getId() {
        return id;
    }

    public Document setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Document setName(String name) {
        this.name = name;
        return this;
    }

    public String getSeries() {
        return series;
    }

    public Document setSeries(String series) {
        this.series = series;
        return this;
    }

    public String getNumber() {
        return number;
    }

    public Document setNumber(String number) {
        this.number = number;
        return this;
    }

//    public Set<URL> getCopies() {
//        return new HashSet<>();
//    }

//    public Document setCopies(Set<URL> copies) {
//       // this.copies = copies;
//        return this;
//    }
}
