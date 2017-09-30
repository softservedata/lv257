package com.softserve.edu.Resources.security.entity;


import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "resource_type_privilege")
public class ResourceTypePrivilege extends GenericPrivilege {

    @Column(name = "Read_Access")
    private boolean readAccess;

    @Column(name = "Update_Access")
    private boolean updateAccess;

    @Column(name = "Delete_Access")
    private boolean deleteAccess;

    @Column(name = "Create_Access")
    private boolean createAccess;

    @Column(name = "Resource_Type_Id")
    private long resourceTypeId;

    @OneToMany(cascade= CascadeType.ALL, mappedBy="resourceTypePrivilege", fetch = FetchType.EAGER)
    private Collection<ResourcePropertyPrivilege> resourcePropertyPrivileges;

    public ResourceTypePrivilege() {
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

    public long getResourceTypeId() {
        return resourceTypeId;
    }

    public void setResourceTypeId(long resourceTypeId) {
        this.resourceTypeId = resourceTypeId;
    }

    public Collection<ResourcePropertyPrivilege> getResourcePropertyPrivileges() {
        return resourcePropertyPrivileges;
    }

    public void setResourcePropertyPrivileges(Collection<ResourcePropertyPrivilege> resourcePropertyPrivileges) {
        this.resourcePropertyPrivileges = resourcePropertyPrivileges;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ResourceTypePrivilege that = (ResourceTypePrivilege) o;

        if (readAccess != that.readAccess) return false;
        if (updateAccess != that.updateAccess) return false;
        if (deleteAccess != that.deleteAccess) return false;
        if (createAccess != that.createAccess) return false;
        return resourceTypeId == that.resourceTypeId;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (readAccess ? 1 : 0);
        result = 31 * result + (updateAccess ? 1 : 0);
        result = 31 * result + (deleteAccess ? 1 : 0);
        result = 31 * result + (createAccess ? 1 : 0);
        result = 31 * result + (int) (resourceTypeId ^ (resourceTypeId >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "ResourceTypePrivilege{" +
                "readAccess=" + readAccess +
                ", updateAccess=" + updateAccess +
                ", deleteAccess=" + deleteAccess +
                ", createAccess=" + createAccess +
                ", resourceTypeId=" + resourceTypeId +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}