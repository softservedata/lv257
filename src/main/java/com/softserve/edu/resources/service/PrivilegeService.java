package com.softserve.edu.services.interfaces;

import com.softserve.edu.entities.Privilege;

public interface PrivilegeService {

    Privilege findByName(String name);

    void delete(Privilege privilege);
}
