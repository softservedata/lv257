package com.softserve.edu.resources.dto;

import java.util.Set;

public class ResourceTypeDTO {
    private String typeName;
    private Set<String> propertiesNames;
    private String pathToRoot;
    private int hierarchyLevel;
    private boolean isLeafType;

    public ResourceTypeDTO(String typeName, Set<String> propertiesNames,
            String pathToRoot, int hierarchyLevel, boolean isLeafType) {
        this.typeName = typeName;
        this.propertiesNames = propertiesNames;
        this.pathToRoot = pathToRoot;
        this.hierarchyLevel = hierarchyLevel;
        this.isLeafType = isLeafType;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Set<String> getPropertiesNames() {
        return propertiesNames;
    }

    public void setPropertiesNames(Set<String> propertiesNames) {
        this.propertiesNames = propertiesNames;
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

    public boolean isLeafType() {
        return isLeafType;
    }

    public void setLeafType(boolean isLeafType) {
        this.isLeafType = isLeafType;
    }

}
