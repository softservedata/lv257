package com.softserve.edu.Resources.entity;

import com.softserve.edu.Resources.Constants;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "RESOURCE_CATEGORIES")
public class ResourceCategory {
    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)

    @Column(name = "Id")
    private Long id;

    @Column(name = "Category_Name", unique = true, nullable = false)
    private String categoryName;

    @ManyToOne
    @JoinColumn(name = "Id_Parent")
    private ResourceCategory parentCategory;

    @OneToMany(mappedBy = "parentCategory", cascade = CascadeType.ALL)
    private Set<ResourceCategory> childrenCategories = new HashSet<>();

    @OneToMany(mappedBy = "category", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    private Set<ResourceType> resourceTypes = new HashSet<>();

    public ResourceCategory(String categoryName) {
        this.categoryName = categoryName;
    }

    public ResourceCategory() {
    }

    public Long getId() {
        return id;
    }

    public ResourceCategory setId(Long id) {
        this.id = id;
        return this;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public ResourceCategory setCategoryName(String categoryName) {
        this.categoryName = categoryName;
        return this;
    }

    public ResourceCategory getParentCategory() {
        return parentCategory;
    }

    public ResourceCategory setParentCategory(ResourceCategory parentCategory) {
        this.parentCategory = parentCategory;
        return this;
    }

    public Set<ResourceCategory> getChildrenCategories() {
        return childrenCategories;
    }

    public ResourceCategory setChildrenCategories(
            Set<ResourceCategory> childrenCategories) {
        this.childrenCategories = childrenCategories;
        return this;
    }

    public Set<ResourceType> getResourceTypes() {
        return resourceTypes;
    }

    public ResourceCategory setResourceTypes(Set<ResourceType> resourceTypes) {
        this.resourceTypes = resourceTypes;
        return this;
    }

    @Override
    public String toString() {
        return "ResourceCategory{" +
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResourceCategory category = (ResourceCategory) o;

        if (id != null ? !id.equals(category.id) : category.id != null) return false;
        return categoryName.equals(category.categoryName);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + categoryName.hashCode();
        return result;
    }

    public ResourceCategory(String categoryName, ResourceCategory parentCategory) {
        this.categoryName = categoryName;
        this.parentCategory = parentCategory;
    }
}
