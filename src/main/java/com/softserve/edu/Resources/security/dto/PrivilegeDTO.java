package com.softserve.edu.Resources.security.dto;

import com.softserve.edu.Resources.security.entity.ResourceTypePrivilege;

public class PrivilegeDTO {
    private String privilegeType;
    private long id;
    private String name;
    private String description;
    private boolean accessible;
    private boolean readAccess;
    private boolean updateAccess;
    private boolean deleteAccess;
    private boolean createAccess;
    private ResourceTypePrivilege resourceTypePrivilege;
    private long resourceTypeId;

    public String getPrivilegeType() {
        return privilegeType;
    }

    public void setPrivilegeType(String privilegeType) {
        this.privilegeType = privilegeType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isAccessible() {
        return accessible;
    }

    public void setAccessible(boolean accessible) {
        this.accessible = accessible;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isReadAccess() {
        return readAccess;
    }

    public void setReadAccess(boolean readAccess) {
        this.readAccess = readAccess;
    }

    public boolean isUpdateAccess() {
        return updateAccess;
    }

    public void setUpdateAccess(boolean updateAccess) {
        this.updateAccess = updateAccess;
    }

    public boolean isDeleteAccess() {
        return deleteAccess;
    }

    public void setDeleteAccess(boolean deleteAccess) {
        this.deleteAccess = deleteAccess;
    }

    public boolean isCreateAccess() {
        return createAccess;
    }

    public void setCreateAccess(boolean createAccess) {
        this.createAccess = createAccess;
    }

    public ResourceTypePrivilege getResourceTypePrivilege() {
        return resourceTypePrivilege;
    }

    public void setResourceTypePrivilege(ResourceTypePrivilege resourceTypePrivilege) {
        this.resourceTypePrivilege = resourceTypePrivilege;
    }

    public long getResourceTypeId() {
        return resourceTypeId;
    }

    public void setResourceTypeId(long resourceTypeId) {
        this.resourceTypeId = resourceTypeId;
    }
}
