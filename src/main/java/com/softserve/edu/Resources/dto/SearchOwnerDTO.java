package com.softserve.edu.Resources.dto;

import java.util.Map;

public class SearchOwnerDTO {

    private String ownerType;
    private Map<String, String> fieldsAndValues;

    public SearchOwnerDTO(String ownerType, Map<String, String> fieldsAndValues) {
        this.ownerType = ownerType;
        this.fieldsAndValues = fieldsAndValues;
    }

    public SearchOwnerDTO() {
    }

    public String getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(String ownerType) {
        this.ownerType = ownerType;
    }

    public Map<String, String> getFieldsAndValues() {
        return fieldsAndValues;
    }

    public void setFieldsAndValues(Map<String, String> fieldsAndValues) {
        this.fieldsAndValues = fieldsAndValues;
    }

    @Override
    public String toString() {
        return "SearchOwnerDTO{" +
                "ownerType='" + ownerType + '\'' +
                ", fieldsAndValues=" + fieldsAndValues +
                '}';
    }
}
