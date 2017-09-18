package edu.softserve.entity;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "RESOURCE_TYPES")
public class ResourceType {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "Type_Name", unique = true, nullable = false)
    private String typeName;

    @NotNull
    @Column(name = "Table_Name", unique = true, nullable = false)
    private String tableName;

    @ManyToOne
    @JoinColumn(name = "Id_Category")
    private ResourceCategory category;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "RESOURCE_TYPES_PROPERTIES",
            joinColumns = @JoinColumn(name = "Resource_Type_Id"),
            inverseJoinColumns = @JoinColumn(name = "Property_Id")
    )
    private Set<ResourceProperty> properties = new HashSet<>();

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

    public long getId() {
        return id;
    }

    public ResourceType setId(long id) {
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

    public ResourceType setProperties(Set<ResourceProperty> properties) {
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
                       .filter(rp -> rp.getTitle().equalsIgnoreCase(propertyName))
                       .findFirst();
    }

    public ResourceType setId(Long id) {
        this.id = id;
        return this;
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
    
    
    
}
