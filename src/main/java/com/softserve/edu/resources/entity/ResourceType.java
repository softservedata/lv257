package com.softserve.edu.resources.entity;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/*
*  ResourceType implements entity containing a set of
*  ResourceProperty that describe particular GenericResource
*
* */
public class ResourceType {

    public static final String GET_ALL_TYPES_AND_PARENTS_QUERY ="WITH TYPES_LINK(ID, Type_Name, ID_Parent, Path, Level) AS ("
            + "SELECT"
            + "ID,"
            + "Type_Name,"
            + "ID_Parent,"
            + "CAST(('/' + Type_Name) AS VARCHAR(MAX)),"
            + "0"
        + "FROM RESOURCE_TYPES WHERE ID_Parent IS NULL"
            + "UNION ALL"
        + "SELECT"
            + "t.ID,"
            + "t.Type_Name,"
            + "t.ID_Parent,"
            + "CAST((tl.Path + '/' + t.Type_Name) AS VARCHAR(MAX)),"
            + "tl.Level + 1"
        + "FROM TYPES_LINK tl"
        + "JOIN RESOURCE_TYPES t ON tl.ID = t.ID_Parent"
    + ")"
    + "SELECT"
        + "ID,"
        + "Type_Name,"
        + "ID_Parent,"
        + "Path,"
        + "Level"
    + "FROM TYPES_LINK"
    + "ORDER BY Path";

    public static final String GET_ALL_TYPES_QUERY ="WITH TYPES_LINK(ID, Type_Name, ID_Parent, Path, Level) AS ("
            + "SELECT"
            + "ID,"
            + "Type_Name,"
            + "ID_Parent,"
            + "CAST(('/' + Type_Name) AS VARCHAR(MAX)),"
            + "0"
        + "FROM RESOURCE_TYPES WHERE ID_Parent IS NULL"
            + "UNION ALL"
        + "SELECT"
            + "t.ID,"
            + "t.Type_Name,"
            + "t.ID_Parent,"
            + "CAST((tl.Path + '/' + t.Type_Name) AS VARCHAR(MAX)),"
            + "tl.Level + 1"
        + "FROM TYPES_LINK tl"
        + "JOIN RESOURCE_TYPES t ON tl.ID = t.ID_Parent"
    + ")"
    + "SELECT"
        + "ID,"
        + "Type_Name,"
        + "ID_Parent,"
        + "Path,"
        + "Level"
    + "FROM TYPES_LINK a"
    + "WHERE NOT EXISTS"
    + "(SELECT * from RESOURCE_TYPES b"
    + "WHERE a.ID = b.ID_Parent)"
    + "ORDER BY Path";

    private int id;
    private String typeName;
    private String tableName;
    private ResourceType parentType;
    private Set<ResourceProperty> properties;
    private String pathToRoot;
    private int hierarchyLevel;

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

    public String getName() {
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
    }

}
