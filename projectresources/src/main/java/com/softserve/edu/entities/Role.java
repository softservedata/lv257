package com.softserve.edu.entities;

import java.util.Collection;

public class Role {
    private int id;
    private String name;
    private Collection<User> users;

    public Role() {
    }

    public int getId() {
        return id;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
