package com.softserve.edu.resources.service;


import com.softserve.edu.resources.entity.Role;

import java.util.List;

public interface RoleService {

    Role findByName(String name);

    void delete(Role role);

    List<Role> getAllRoles();
}
