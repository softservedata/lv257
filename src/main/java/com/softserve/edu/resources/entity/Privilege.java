package com.softserve.edu.resources.entity;

public class Privilege {

    private Long id;
    private String name;

    public Privilege() {
    }

    public Privilege(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
