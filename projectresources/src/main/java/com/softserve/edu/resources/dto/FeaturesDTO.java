package com.softserve.edu.resources.dto;

public class FeaturesDTO {

    private int id;
    private int id_method_ect;
    private String action_name;

    public FeaturesDTO(int id, int id_method_ect, String action_name) {
        this.id = id;
        this.id_method_ect = id_method_ect;
        this.action_name = action_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_method_ect() {
        return id_method_ect;
    }

    public void setId_method_ect(int id_method_ect) {
        this.id_method_ect = id_method_ect;
    }

    public String getAction_name() {
        return action_name;
    }

    public void setAction_name(String action_name) {
        this.action_name = action_name;
    }
}
