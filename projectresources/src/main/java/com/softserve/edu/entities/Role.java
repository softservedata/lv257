package com.softserve.edu.entities;

import java.util.List;

public class Role {
    private int id;
    private String name;
    private String community_restrided;
    private List<User> users;

    public Role() {
    }

    public int getId() {
        return id;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
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

    public String getCommunity_restrided() {
        return community_restrided;
    }

    public void setCommunity_restrided(String community_restrided) {
        this.community_restrided = community_restrided;
    }
}
/*
Proposals:
1) change: class name from Role to Roles (line 5)
2) change: restrided to restricted

*/
