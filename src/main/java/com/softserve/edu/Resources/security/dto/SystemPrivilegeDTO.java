package com.softserve.edu.Resources.security.dto;


import com.softserve.edu.Resources.security.entity.SystemPrivilege;

import java.util.List;

public class SystemPrivilegeDTO {
    List<SystemPrivilege> systemPrivileges;

    public List<SystemPrivilege> getSystemPrivileges() {
        return systemPrivileges;
    }

    public void setSystemPrivileges(List<SystemPrivilege> systemPrivileges) {
        this.systemPrivileges = systemPrivileges;
    }
}
