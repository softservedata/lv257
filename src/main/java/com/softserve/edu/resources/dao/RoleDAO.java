package com.softserve.edu.resources.dao;



import com.softserve.edu.resources.entity.Role;

import java.util.List;

public interface RoleDAO {

    Role findByName(String name);

    void delete(Role role);

    List<Role> getAllRoles();
}
