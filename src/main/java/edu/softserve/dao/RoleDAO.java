package edu.softserve.dao;

import edu.softserve.entity.Role;

public interface RoleDAO  {
    Role findByName(String name);

    void delete(Role role);
}
