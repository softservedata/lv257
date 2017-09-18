package edu.softserve.entity;

public class PropertyValue {

    private int id;
    private ResourceProperty type;
    private String value;
    
    public PropertyValue(int id, ResourceProperty type, String value) {
        this.id = id;
        this.type = type;
        this.value = value;
    }

    public PropertyValue() {
       
    }
    
    
    
    public PropertyValue(ResourceProperty type, String value) {
        
        this.type = type;
        this.value = value;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public ResourceProperty getType() {
        return type;
    }
    public void setType(ResourceProperty type) {
        this.type = type;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "PropertyValue [id=" + id + ", type=" + type + ", value=" + value + "]";
    }
    
    
    
}
