package edu.softserve.dao;

import edu.softserve.entity.Role;

import java.util.List;

public interface RoleDAO  {
    Role findByName(String name);

    void delete(Role role);

     List<Role> getAllRoles();
}
