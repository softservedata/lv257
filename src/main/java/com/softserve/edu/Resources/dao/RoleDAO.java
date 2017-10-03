package com.softserve.edu.Resources.dao;

import com.softserve.edu.Resources.entity.Role;

import java.util.List;

public interface RoleDAO  {
    Role findByName(String name);

    void delete(Role role);

     List<Role> getAllRoles();
}
