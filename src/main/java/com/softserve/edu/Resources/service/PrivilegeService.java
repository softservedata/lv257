package com.softserve.edu.Resources.service;


import com.softserve.edu.Resources.entity.Privilege;

import java.util.List;

public interface PrivilegeService {

    public Privilege getPrivilegeById(Long id);
    public Privilege addPrivilege(String s);

    public List<String> getAllPrivileges();

    public void deletePrivilege();
}
