package com.softserve.edu.resources.service;


import com.softserve.edu.resources.entity.Privilege;

public interface PrivilegeService {

    Privilege findByName(String name);

    void delete(Privilege privilege);
}
