package com.softserve.edu.Resources.dto;

import java.util.Map;

public class GenericResourceDTO {

    private long id;
    private Map<String, String> resourcePropertyValue;

    public GenericResourceDTO() {
        // TODO Auto-generated constructor stub
    }

    public GenericResourceDTO(long id, Map<String, String> resourcePropertyValue) {

        this.id = id;
        this.resourcePropertyValue = resourcePropertyValue;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Map<String, String> getResourcePropertyValue() {
        return resourcePropertyValue;
    }

    public void setResourcePropertyValue(Map<String, String> resourcePropertyValue) {
        this.resourcePropertyValue = resourcePropertyValue;
    }

}
