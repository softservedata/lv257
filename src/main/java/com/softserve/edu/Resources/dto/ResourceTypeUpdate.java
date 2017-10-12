package com.softserve.edu.Resources.dto;

import java.util.ArrayList;
import java.util.List;

public class ResourceTypeUpdate {

    private Long id;
    private Long categoryId;
    private String typeName;
    private String tableName;
    private List<ConstrainedPropertyBrief> properties = new ArrayList<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<ConstrainedPropertyBrief> getProperties() {
        return properties;
    }

    public void setProperties(List<ConstrainedPropertyBrief> properties) {
        this.properties = properties;
    }

}