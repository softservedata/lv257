package com.softserve.edu.Resources.dto;

import com.fasterxml.jackson.annotation.*;

import java.util.HashSet;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
//@JsonIdentityInfo(generator = ObjectIdGenerat4ors.PropertyGenerator.class, property = "id")
public class ResourceCategoryDTO {
    private Long id;

    @JsonProperty("catname")
    private String categoryName;

    @JsonBackReference
//    @JsonProperty("parent_id")
//    @JsonIdentityReference(alwaysAsId = true)
    private ResourceCategoryDTO parentCategory;

    @JsonManagedReference
    @JsonProperty("children")
    private Set<ResourceCategoryDTO> childrenCategories = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public ResourceCategoryDTO getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(ResourceCategoryDTO parentCategory) {
        this.parentCategory = parentCategory;
    }

    public Set<ResourceCategoryDTO> getChildrenCategories() {
        return childrenCategories;
    }

    public void setChildrenCategories(Set<ResourceCategoryDTO> childrenCategories) {
        this.childrenCategories = childrenCategories;
    }

    @Override
    public String toString() {
        return "ResourceCategoryDTO{" +
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                ", parentCategory=" + parentCategory +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResourceCategoryDTO that = (ResourceCategoryDTO) o;

        return categoryName.equals(that.categoryName);
    }

    @Override
    public int hashCode() {
        return categoryName.hashCode();
    }
}

