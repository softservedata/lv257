package com.softserve.edu.Resources.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "RESOURCE_CATEGORIES")
public class ResourceCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private Long id;

    @Column(name = "Category_Name"/*, unique = true*/)
    @JsonProperty("catname")
    private String categoryName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    @JoinColumn(name = "Id_Parent")
    private ResourceCategory parentCategory;

    @OneToMany(mappedBy = "parentCategory")
    @JsonManagedReference
    @JsonProperty("children")
    private Set<ResourceCategory> childrenCategories = new HashSet<>();

    @Column(name = "Hierarchy_Level")
    @JsonProperty("level")
    private Integer hierarchyLevel;

    @Column(name = "Path_To_Root")
    @JsonProperty("rootpath")
    private String pathToRoot;

    @OneToMany(mappedBy = "category")
    @JsonManagedReference
    @JsonProperty("restypes")
    private Set<ResourceType> resourceTypes = new HashSet<>();

    public ResourceCategory(String categoryName) {
        this.categoryName = categoryName;
    }

    public ResourceCategory() {
    }

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

    public ResourceCategory getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(ResourceCategory parentCategory) {
        this.parentCategory = parentCategory;
    }

    public Set<ResourceCategory> getChildrenCategories() {
        return childrenCategories;
    }

    public void setChildrenCategories(
            Set<ResourceCategory> childrenCategories) {
        this.childrenCategories = childrenCategories;
    }

    public Integer getHierarchyLevel() {
        return hierarchyLevel;
    }

    public void setHierarchyLevel(Integer hierarchyLevel) {
        this.hierarchyLevel = hierarchyLevel;
    }

    public String getPathToRoot() {
        return pathToRoot;
    }

    public void setPathToRoot(String pathToRoot) {
        this.pathToRoot = pathToRoot;
    }

    public Set<ResourceType> getResourceTypes() {
        return resourceTypes;
    }

    public void setResourceTypes(Set<ResourceType> resourceTypes) {
        this.resourceTypes = resourceTypes;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Resource Category [name=").append(categoryName).append("]").append("[id=").append(id).append("]");
        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResourceCategory that = (ResourceCategory) o;

        return categoryName.equals(that.categoryName);
    }

    @Override
    public int hashCode() {
        return categoryName.hashCode();
    }

    public ResourceCategory(String categoryName, ResourceCategory parentCategory, Set<ResourceType> resourceTypes) {
        this.categoryName = categoryName;
        this.parentCategory = parentCategory;
        this.resourceTypes = resourceTypes;
    }
}
