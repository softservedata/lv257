package com.softserve.edu.Resources.security.dao;

import com.softserve.edu.Resources.dao.GenericDAO;
import com.softserve.edu.Resources.security.entity.GenericPrivilege;

import java.util.List;

public interface GenericPrivilegeDAO extends GenericDAO<GenericPrivilege, Long> {

    void addGenericPrivilege(GenericPrivilege privilege);

    void updateGenericPrivilege(GenericPrivilege privilege);

    List<GenericPrivilege> getAllSystemPrivileges();

    List<GenericPrivilege> getAllResourceTypePrivileges();

    List<GenericPrivilege> getAllResourcePropertyPrivileges();

    List<GenericPrivilege> getAllPrivileges();

    GenericPrivilege getGenericPrivilegeById(long id);
}
