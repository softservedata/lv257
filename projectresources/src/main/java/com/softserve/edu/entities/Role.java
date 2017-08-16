package com.softserve.edu.entities;

public class Role {
    private int id;
    private String name;
    private String community_restrided;

    public Role() {
    }

    public int getId() {
        return id;
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
