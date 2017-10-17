package com.softserve.edu.Resources.dto;

import com.fasterxml.jackson.annotation.*;

import java.util.HashSet;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ResourceCategoryDTO{

    @JsonView(Views.Categories.class)
    private Long id;

    @JsonProperty("categoryname")
    @JsonView(Views.Categories.class)
    private String categoryName;

    @JsonBackReference
    @JsonView(Views.Categories.class)
    private ResourceCategoryDTO parentCategory;

    @JsonManagedReference
    @JsonProperty("children")
    @JsonView(Views.Categories.class)
    private Set<ResourceCategoryDTO> childrenCategories = new HashSet<>();

    @JsonManagedReference
    @JsonProperty("restypes")
    @JsonView(Views.CategoriesWithTypes.class)
    private Set<ResourceTypeDTO> instantiatedResourceTypes = new HashSet<>();

    @JsonProperty("hasTypes")
    @JsonView(Views.Categories.class)
    private boolean withResourceTypes;

    public ResourceCategoryDTO() {
    }

    public ResourceCategoryDTO(String categoryName, ResourceCategoryDTO parentCategory) {
        this.categoryName = categoryName;
        this.parentCategory = parentCategory;
    }

    public Long getId() {
        return id;
    }

    public ResourceCategoryDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public ResourceCategoryDTO setCategoryName(String categoryName) {
        this.categoryName = categoryName;
        return this;
    }

    public ResourceCategoryDTO getParentCategory() {
        return parentCategory;
    }

    public ResourceCategoryDTO setParentCategory(ResourceCategoryDTO parentCategory) {
        this.parentCategory = parentCategory;
        return this;
    }

    public Set<ResourceCategoryDTO> getChildrenCategories() {
        return childrenCategories;
    }

    public ResourceCategoryDTO setChildrenCategories(Set<ResourceCategoryDTO> childrenCategories) {
        this.childrenCategories = childrenCategories;
        return this;
    }

    public Set<ResourceTypeDTO> getInstantiatedResourceTypes() {
        return instantiatedResourceTypes;
    }

    public ResourceCategoryDTO setInstantiatedResourceTypes(Set<ResourceTypeDTO> instantiatedResourceTypes) {
        this.instantiatedResourceTypes = instantiatedResourceTypes;
        return this;
    }

    public boolean isWithResourceTypes() {
        return withResourceTypes;
    }

    public ResourceCategoryDTO setWithResourceTypes(boolean withResourceTypes) {
        this.withResourceTypes = withResourceTypes;
        return this;
    }

    @Override
    public String toString() {
        return "ResourceCategoryDTO{" +
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResourceCategoryDTO that = (ResourceCategoryDTO) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return categoryName.equals(that.categoryName);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + categoryName.hashCode();
        return result;
    }
}

