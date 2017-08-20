package com.softserve.edu.resources.dto;

public class PermissionDTO {

    private int id;
    private int id_role;
    private int id_feature;
    private String alowed;

    public PermissionDTO(int id, int id_role, int id_feature, String alowed) {
        this.id = id;
        this.id_role = id_role;
        this.id_feature = id_feature;
        this.alowed = alowed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_role() {
        return id_role;
    }

    public void setId_role(int id_role) {
        this.id_role = id_role;
    }

    public int getId_feature() {
        return id_feature;
    }

    public void setId_feature(int id_feature) {
        this.id_feature = id_feature;
    }

    public String getAlowed() {
        return alowed;
    }

    public void setAlowed(String alowed) {
        this.alowed = alowed;
    }
}
