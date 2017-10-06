package com.softserve.edu.Resources.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.softserve.edu.Resources.Constants;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

/*
*  ResourceType represents entity containing a set of
*  ResourceProperty that describe particular GenericResource
*
* */
@Entity
@Table(name = "RESOURCE_TYPES")
public class ResourceType {

    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    @Column(name = "Id")
    private Long id;

    @JsonProperty("typename")
    @NotNull
    @Column(name = "Type_Name", unique = true, nullable = false)
    private String typeName;

    @JsonIgnore
    @NotNull
    @Column(name = "Table_Name", unique = true, nullable = false)
    private String tableName;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "Id_Category", nullable = false)
    private ResourceCategory category;

    @JsonIgnore
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "Type_Properties",
            joinColumns = @JoinColumn(name = "Type_Id")
    )
    private Set<ConstrainedProperty> properties = new HashSet<>();

    @JsonIgnore
    @Column(name = "Instantiated")
    private boolean instantiated;


    public ResourceType() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        ResourceType that = (ResourceType) o;

        return typeName.equals(that.typeName);
    }

    @Override
    public int hashCode() {
        return typeName.hashCode();
    }

    public ResourceType(String typeName) {
        this.typeName = typeName;
        properties = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public ResourceType setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTypeName() {
        return typeName;
    }

    public ResourceType setTypeName(String typeName) {
        this.typeName = typeName;
        return this;
    }

    public String getTableName() {
        return tableName;
    }

    public ResourceType setTableName(String tableName) {
        this.tableName = tableName;
        return this;
    }

    public String getName() {
        return typeName;
    }

    public Set<ConstrainedProperty> getProperties() {
        return Collections.unmodifiableSet(properties);
    }

    public void addProperty(ConstrainedProperty constrainedProperty) {
        properties.add(constrainedProperty);
    }

    public void removeProperty(ConstrainedProperty constrainedProperty) {
        properties.remove(constrainedProperty);
    }

    public Optional<ConstrainedProperty> getProperty(String propertyName) {
        return properties.stream()
                       .filter(rp -> rp.getProperty().getTitle().equalsIgnoreCase(propertyName))
                       .findFirst();
    }

    public ResourceCategory getCategory() {
        return category;
    }

    public ResourceType setCategory(ResourceCategory category) {
        this.category = category;
        return this;
    }

    public boolean isInstantiated() {
        return instantiated;
    }

    public ResourceType setInstantiated(boolean instantiated) {
        this.instantiated = instantiated;
        return this;
    }

    @Override
    public String toString() {
        return "ResourceType [id=" + id + ", typeName=" + typeName + ", tableName=" + tableName + ", category="
                + category + ", properties=" + properties + ", instantiated=" + instantiated + "]";
    }
    
    
}
