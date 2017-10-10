package com.softserve.edu.Resources.dto;

import java.util.HashSet;
import java.util.Set;

public class ResourceTypeUpdate {
    private long id;
    private long categoryId;
    private String typeName;
    private String tableName;
    private Set<ConstrainedPropertyBrief> properties = new HashSet<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Set<ConstrainedPropertyBrief> getProperties() {
        return properties;
    }

    public void setProperties(Set<ConstrainedPropertyBrief> properties) {
        this.properties = properties;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }
}
