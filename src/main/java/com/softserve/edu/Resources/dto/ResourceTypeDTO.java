package com.softserve.edu.Resources.dto;

public class ResourceTypeDTO {

    private long id;
    private String typeName;
    private String tableName;
    
    public ResourceTypeDTO() {
       
    }
    
    public ResourceTypeDTO(int id, String typeName, String tableName) {
        
        this.id = id;
        this.typeName = typeName;
        this.tableName = tableName;
    }
    
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getTypeName() {
        return typeName;
    }
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
    public String getTableName() {
        return tableName;
    }
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    
    
    
    
    
}
