package edu.softserve.dao;

import edu.softserve.entity.Privilege;

public interface PrivilegeDAO {
    Privilege findByName(String name);

    void delete(Privilege privilege);
}
