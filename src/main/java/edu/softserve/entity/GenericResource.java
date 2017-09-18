package edu.softserve.entity;

import java.util.Set;

public class GenericResource {

    private int id;
    private int id_Address;
    private Set <PropertyValue> resourceValues;
    
    
    public GenericResource() {
      
    }


    public GenericResource(int id, int id_Address, Set<PropertyValue> resourceValues) {
        
        this.id = id;
        this.id_Address = id_Address;
        this.resourceValues = resourceValues;
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public int getId_Address() {
        return id_Address;
    }


    public void setId_Address(int id_Address) {
        this.id_Address = id_Address;
    }


    public Set<PropertyValue> getResourceValues() {
        return resourceValues;
    }


    public void setResourceValues(Set<PropertyValue> resourceValues) {
        this.resourceValues = resourceValues;
    }
    
    
    
    
}
