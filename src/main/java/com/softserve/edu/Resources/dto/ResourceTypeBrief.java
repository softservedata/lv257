package com.softserve.edu.Resources.dto;

import com.softserve.edu.Resources.entity.ResourceType;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ResourceTypeBrief {

    private Long id;
    private Long categoryId;
    private String typeName;
    private String tableName;
    private Set<ConstrainedPropertyBrief> properties = new HashSet<>();

    public ResourceTypeBrief() {
    }

    public ResourceTypeBrief(ResourceType resourceType) {
        id = resourceType.getId();
        categoryId = resourceType.getCategory().getId();
        typeName = resourceType.getTypeName();
        tableName = resourceType.getTableName();
        properties = resourceType.getProperties().stream()
                             .map(ConstrainedPropertyBrief::new)
                             .collect(Collectors.toSet());
    }

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

    public Set<ConstrainedPropertyBrief> getProperties() {
        return properties;
    }

    public void setProperties(Set<ConstrainedPropertyBrief> properties) {
        this.properties = properties;
    }

}