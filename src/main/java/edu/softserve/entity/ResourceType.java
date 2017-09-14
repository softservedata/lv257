package edu.softserve.entity;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/*
*  ResourceType represents entity containing a set of
*  ResourceProperty that describe particular GenericResource
*
* */
@Entity
@Table(name = "RESOURCE_TYPES")
public class ResourceType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private int id;

    @Column(name = "Type_Name")
    private String typeName;

    @Column(name = "Table_Name")
    private String tableName;

    @ManyToOne
    @JoinColumn(name = "Id_Category")
    private ResourceCategory resourceCategory;
    /*private Set<ResourceProperty> properties;*/

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
        /*properties = new HashSet<>();*/
    }

  public int getId() {
    return id;
  }

  public ResourceType setId(int id) {
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

  public ResourceCategory getResourceCategory() {
    return resourceCategory;
  }

  public ResourceType setResourceCategory(ResourceCategory resourceCategory) {
    this.resourceCategory = resourceCategory;
    return this;
  }

/*  public ResourceType setProperties(Set<ResourceProperty> properties) {
    this.properties = properties;
    return this;
  }public String getName() {
    return typeName;
  }

    public Set<ResourceProperty> getProperties() {
        return Collections.unmodifiableSet(properties);
    }

    public boolean addProperty(ResourceProperty resourceProperty) {
        return properties.add(resourceProperty);
    }

    public boolean removeProperty(ResourceProperty resourceProperty) {
        return properties.remove(resourceProperty);
    }

    public Optional<ResourceProperty> getProperty(String propertyName) {
        return properties.stream()
                .filter(rp -> rp.getName().equalsIgnoreCase(propertyName))
                .findFirst();
    }

    public boolean isInstanceType() {
        return !properties.isEmpty();
    }*/

}
