package com.softserve.edu.Resources.entity;

import com.softserve.edu.Resources.Constants;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/*
*  ResourceType represents entity containing a set of
*  ResourceProperty that describes particular GenericResource
*
* */
@Entity
@NamedEntityGraph(
        name = "TypesProperties",
        attributeNodes = {@NamedAttributeNode("properties")}
)
@Table(name = "RESOURCE_TYPES")
public class ResourceType {

    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    @Column(name = "Id")
    private Long id;

    @NotNull
    @Column(name = "Type_Name", unique = true, nullable = false)
    private String typeName;

    @NotNull
    @Column(name = "Table_Name", unique = true, nullable = false)
    private String tableName;

    @ManyToOne
    @JoinColumn(name = "Id_Category", nullable = false)
    private ResourceCategory category;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "Type_Properties",
            joinColumns = @JoinColumn(name = "Type_Id")
    )
    private Set<ConstrainedProperty> properties = new HashSet<>();

    @Column(name = "Instantiated")
    private boolean instantiated;

    @ManyToOne
    @JoinColumn(name = "Id_Assigner", nullable = false)
    private User assigner;

    @OneToOne(mappedBy = "resourceType")
    private ResourceRequest request;



    public void setProperties(Set<ConstrainedProperty> properties) {
        this.properties = properties;
    }

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

    public User getAssigner() {
        return assigner;
    }

    public ResourceType setAssigner(User assigner) {
        this.assigner = assigner;
        return this;
    }

    @Override
    public String toString() {
        return "ResourceType [id=" + id + ", typeName=" + typeName + ", tableName=" + tableName + ", category="
                + category + ", properties=" + properties + ", instantiated=" + instantiated
                + ", assigner=" + assigner + "]";
    }


}
