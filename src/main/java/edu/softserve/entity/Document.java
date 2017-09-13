package edu.softserve.entity;

import javax.persistence.*;
import java.net.URL;
import java.util.Set;

/**
 * Represents legal document issued by authorities
 */

@Entity
@Table(name = "Documents")
public class Document {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "series", nullable = false)
    private String series;

    @Column(name = "number", nullable = false)
    private String number;




    public Document() {
        super();
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



}
