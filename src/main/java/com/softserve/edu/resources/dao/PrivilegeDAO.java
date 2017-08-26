package com.softserve.edu.resources.dao;


import com.softserve.edu.resources.entity.Privilege;

public interface PrivilegeDAO {

    Privilege findByName(String name);

    void delete(Privilege privilege);
}
