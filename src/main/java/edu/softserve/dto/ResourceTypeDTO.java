package edu.softserve.dto;

public class ResourceTypeDTO {

    private int id;
    private String typeName;
    private String tableName;
    
    public ResourceTypeDTO() {
       
    }
    
    public ResourceTypeDTO(int id, String typeName, String tableName) {
        
        this.id = id;
        this.typeName = typeName;
        this.tableName = tableName;
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
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
