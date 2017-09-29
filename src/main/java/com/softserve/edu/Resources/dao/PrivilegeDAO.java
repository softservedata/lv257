package com.softserve.edu.Resources.dao;

import com.softserve.edu.Resources.entity.Privilege;

import java.util.List;

public interface PrivilegeDAO {
    Privilege findByName(String name);

    void delete(Privilege privilege);

    Privilege addPrivilege(String s);

    List<Privilege> getAllPrivileges();
}
