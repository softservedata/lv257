package com.softserve.edu.Resources.security.entity;

import javax.persistence.*;

@Entity
@Table(name = "resource_property_privilege")
public class ResourcePropertyPrivilege extends GenericPrivilege {

    @Column(name = "Read_Access")
    private boolean readAccess;

    @Column(name = "Update_Access")
    private boolean updateAccess;
/*
    @Column(name = "Resource_Type_Privilege_Id")
    @NotEmpty
    private long resourceTypePrivilegeId;*/

    @ManyToOne
    @JoinColumn(name = "Resource_Type_Privilege_Id")
    private ResourceTypePrivilege resourceTypePrivilege;

    public ResourcePropertyPrivilege() {
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
/*
    public long getResourceTypePrivilegeId() {
        return resourceTypePrivilegeId;
    }

    public void setResourceTypePrivilegeId(long resourceTypePrivilegeId) {
        this.resourceTypePrivilegeId = resourceTypePrivilegeId;
    }*/

    public ResourceTypePrivilege getResourceTypePrivilege() {
        return resourceTypePrivilege;
    }

    public void setResourceTypePrivilege(ResourceTypePrivilege resourceTypePrivilege) {
        this.resourceTypePrivilege = resourceTypePrivilege;
    }



}