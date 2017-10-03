package com.softserve.edu.Resources.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.softserve.edu.Resources.entity.ResourceType;

public class ResourceTypeDTO {


    @JsonView(Views.CategorySelectingWithTypes.class)
    private long id;

    @JsonView(Views.CategorySelectingWithTypes.class)
    private String typeName;

    @JsonView(Views.CategorySelectingWithTypes.class)
    private String tableName;

    @JsonBackReference
    @JsonView(Views.CategorySelectingWithTypes.class)
    private ResourceCategoryDTO category;
    
    public ResourceTypeDTO() {
    }
    
    public ResourceTypeDTO(Long id, String typeName, String tableName) {
        this.id = id;
        this.typeName = typeName;
        this.tableName = tableName;
    }

    public ResourceTypeDTO(ResourceType resourceType) {
        this.id = resourceType.getId();
        this.typeName = resourceType.getTypeName();
        this.tableName = resourceType.getTableName();
    }
    
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

    public ResourceCategoryDTO getCategory() {
        return category;
    }

    public void setCategory(ResourceCategoryDTO category) {
        this.category = category;
    }
}
