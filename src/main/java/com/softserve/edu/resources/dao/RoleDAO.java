package com.softserve.edu.dao.interfaces;

import com.softserve.edu.entities.Role;

import java.util.List;

public interface RoleDAO {

    Role findByName(String name);

    void delete(Role role);

    List<Role> getAllRoles();
}
