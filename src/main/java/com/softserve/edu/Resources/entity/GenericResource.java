package com.softserve.edu.Resources.entity;import com.softserve.edu.Resources.Constants;

import java.util.*;
import java.util.stream.Collectors;

public class GenericResource {

    private int id;
    // private Set<Owner> owners;
    private int id_address;
    private Set<PropertyValue> propertyValues;
    
//    private ResourceType type;
//    private Map<ResourceProperty, PropertyValue> resourceValues = new HashMap<>();

    public GenericResource() {
    }

    /**
     * Create instance implementing {@code Resource} described with
     * {@code resourceValues}.
     *
     * @param resourceType
     *            type of particular Resource
     * @param resourceValues
     *            {@code Set} of {@code PropertyValue}s describing resource
     */
//    public GenericResource(ResourceType resourceType, Set<PropertyValue> resourceValues) {
//        type = resourceType;
//        this.resourceValues = resourceValues.stream().collect(Collectors.toMap(PropertyValue::getType, value -> value));
//    }

    public int getId() {
        return id;
    }

    public GenericResource setId(int id) {
        this.id = id;
        return this;
    }

    /*
     * public GenericResource setOwners(Set<Owner> owners) { this.owners =
     * owners; return this; }
     */

//    public GenericResource setType(ResourceType type) {
//        this.type = type;
//        return this;
//    }
//
//    public Map<ResourceProperty, PropertyValue> getResourceValues() {
//        return resourceValues;
//    }
//
//    public ResourceType getType() {
//        return type;
//    }

    /*
     * @Override public Set<Owner> getOwners() { return
     * Collections.unmodifiableSet(owners); }
     */

//    public GenericResource setResourceValues(Map<ResourceProperty, PropertyValue> resourceValues) {
//        this.resourceValues = resourceValues;
//        return this;
//    }
//
//    @Override
//    public Optional<PropertyValue> getValue(ResourceProperty resourceType) {
//        return Optional.ofNullable(resourceValues.get(resourceType));
//    }
//
//    @Override
//    public void setValue(ResourceProperty resourceProperty, PropertyValue value) {
//        resourceValues.put(resourceProperty, value);
//    }
//
//    @Override
//    public Collection<PropertyValue> getValues() {
//        return Collections.unmodifiableCollection(resourceValues.values());
//    }
//
//    @Override
//    public void setValues(Set<PropertyValue> values) {
//        values.forEach(value -> resourceValues.put(value.getType(), value));
//    }

    public int getId_Address() {
        return id_address;
    }

    public void setId_Address(int id_address) {
        this.id_address = id_address;
    }

    public Set<PropertyValue> getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(Set<PropertyValue> propertyValues) {
        this.propertyValues = propertyValues;
    }

    @Override
    public String toString() {
        return "GenericResource [id=" + id + ", id_address=" + id_address + ", propertyValues=" + propertyValues + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + id_address;
        result = prime * result + ((propertyValues == null) ? 0 : propertyValues.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        GenericResource other = (GenericResource) obj;
        if (id != other.id)
            return false;
        if (id_address != other.id_address)
            return false;
        if (propertyValues == null) {
            if (other.propertyValues != null)
                return false;
        } else if (!propertyValues.equals(other.propertyValues))
            return false;
        return true;
    }

    
    
}
