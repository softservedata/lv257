package edu.softserve.entity;

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
    private String categoryName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Id_Parent")
    private ResourceCategory parentCategory;

    @OneToMany(mappedBy = "parentCategory")
    private Set<ResourceCategory> childrenCategories = new HashSet<>();

    @Column(name = "Hierarchy_Level")
    private Integer hierarchyLevel;

    @Column(name = "Path_To_Root")
    private String pathToRoot;

    @OneToMany(mappedBy = "resourceCategory")
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

    public ResourceCategory(String categoryName, ResourceCategory parentCategory, Set<ResourceType> resourceTypes) {
        this.categoryName = categoryName;
        this.parentCategory = parentCategory;
        this.resourceTypes = resourceTypes;
    }
}
