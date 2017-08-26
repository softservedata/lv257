package com.softserve.edu.dao.interfaces;

import com.softserve.edu.entities.Privilege;

public interface PrivilegeDAO {

    Privilege findByName(String name);

    void delete(Privilege privilege);
}
