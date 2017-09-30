package com.softserve.edu.Resources.security.dto;


import com.softserve.edu.Resources.security.entity.ResourceTypePrivilege;

import java.util.List;

public class ResourceTypePrivilegeDTO {
    List<ResourceTypePrivilege> resourceTypePrivileges;

    public List<ResourceTypePrivilege> getResourceTypePrivileges() {
        return resourceTypePrivileges;
    }

    public void setResourceTypePrivileges(List<ResourceTypePrivilege> resourceTypePrivileges) {
        this.resourceTypePrivileges = resourceTypePrivileges;
    }
}
