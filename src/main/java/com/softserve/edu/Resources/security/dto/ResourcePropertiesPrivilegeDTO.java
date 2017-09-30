package com.softserve.edu.Resources.security.dto;


import com.softserve.edu.Resources.security.entity.ResourcePropertyPrivilege;

import java.util.List;

public class ResourcePropertiesPrivilegeDTO {
    List<ResourcePropertyPrivilege> resourcePropertyPrivileges;

    public List<ResourcePropertyPrivilege> getResourcePropertyPrivileges() {
        return resourcePropertyPrivileges;
    }

    public void setResourcePropertyPrivileges(List<ResourcePropertyPrivilege> resourcePropertyPrivileges) {
        this.resourcePropertyPrivileges = resourcePropertyPrivileges;
    }
}
