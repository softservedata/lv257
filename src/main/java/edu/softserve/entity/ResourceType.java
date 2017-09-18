package edu.softserve.entity;

import java.util.Set;

public class ResourceType {

    private int id;
    private String typeName;
    private String tableName;
    private ResourceType parentType;
    private Set<ResourceProperty> properties;
    private String pathToRoot;
    private int hierarchyLevel;
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
    public ResourceType getParentType() {
        return parentType;
    }
    public void setParentType(ResourceType parentType) {
        this.parentType = parentType;
    }
    public Set<ResourceProperty> getProperties() {
        return properties;
    }
    public void setProperties(Set<ResourceProperty> properties) {
        this.properties = properties;
    }
    public String getPathToRoot() {
        return pathToRoot;
    }
    public void setPathToRoot(String pathToRoot) {
        this.pathToRoot = pathToRoot;
    }
    public int getHierarchyLevel() {
        return hierarchyLevel;
    }
    public void setHierarchyLevel(int hierarchyLevel) {
        this.hierarchyLevel = hierarchyLevel;
    }
    
    
    
}
