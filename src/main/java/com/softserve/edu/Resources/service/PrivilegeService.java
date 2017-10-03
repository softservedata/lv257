package com.softserve.edu.Resources.service;


import com.softserve.edu.Resources.entity.Privilege;
import com.softserve.edu.Resources.entity.PrivilegeType;

import java.util.List;

public interface PrivilegeService {

    public Privilege getPrivilegeById(Long id);
    public Privilege addPrivilege(String s);
    public Privilege addPrivilege(String s, PrivilegeType privilegeType);

    public List<Privilege> getAllPrivileges();

    public void deletePrivilege();
    public void deleteAllPrivilege();
}
