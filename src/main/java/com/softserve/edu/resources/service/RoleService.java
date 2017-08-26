package com.softserve.edu.services.interfaces;

import com.softserve.edu.entities.Role;

import java.util.List;

public interface RoleService {

    Role findByName(String name);

    void delete(Role role);

    List<Role> getAllRoles();
}
