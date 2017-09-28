package com.softserve.edu.Resources.dto;

import java.util.Map;

public class GenericResourceDTO {

    private int id;
    private Map<String, String> resourcePropertyValue;

    public GenericResourceDTO() {
        // TODO Auto-generated constructor stub
    }

    public GenericResourceDTO(int id, Map<String, String> resourcePropertyValue) {

        this.id = id;
        this.resourcePropertyValue = resourcePropertyValue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Map<String, String> getResourcePropertyValue() {
        return resourcePropertyValue;
    }

    public void setResourcePropertyValue(Map<String, String> resourcePropertyValue) {
        this.resourcePropertyValue = resourcePropertyValue;
    }

}
