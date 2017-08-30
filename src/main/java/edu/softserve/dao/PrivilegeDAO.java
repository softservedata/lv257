package edu.softserve.dao;

import edu.softserve.entity.Privilege;
import edu.softserve.entity.Role;

import java.util.List;

public interface PrivilegeDAO {
    Privilege findByName(String name);

    void delete(Privilege privilege);

    Privilege addPrivilege(Privilege privilege);

    List<Privilege> getAllPrivileges();
}
