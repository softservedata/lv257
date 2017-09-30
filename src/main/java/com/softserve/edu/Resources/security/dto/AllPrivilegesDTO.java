package com.softserve.edu.Resources.security.dto;


import com.softserve.edu.Resources.security.entity.ResourcePropertyPrivilege;
import com.softserve.edu.Resources.security.entity.ResourceTypePrivilege;
import com.softserve.edu.Resources.security.entity.SystemPrivilege;

import java.util.List;

public class AllPrivilegesDTO {
    List<SystemPrivilege> systemPrivileges;
    List<ResourceTypePrivilege> resourceTypePrivileges ;
    List<ResourcePropertyPrivilege> resourcePropertyPrivileges;

    public List<SystemPrivilege> getSystemPrivileges() {
        return systemPrivileges;
    }

    public void setSystemPrivileges(List<SystemPrivilege> systemPrivileges) {
        this.systemPrivileges = systemPrivileges;
    }

    public List<ResourceTypePrivilege> getResourceTypePrivileges() {
        return resourceTypePrivileges;
    }

    public void setResourceTypePrivileges(List<ResourceTypePrivilege> resourceTypePrivileges) {
        this.resourceTypePrivileges = resourceTypePrivileges;
    }

    public List<ResourcePropertyPrivilege> getResourcePropertyPrivileges() {
        return resourcePropertyPrivileges;
    }

    public void setResourcePropertyPrivileges(List<ResourcePropertyPrivilege> resourcePropertyPrivileges) {
        this.resourcePropertyPrivileges = resourcePropertyPrivileges;
    }
}
