package com.softserve.edu.Resources.security.service;

import com.softserve.edu.Resources.security.dto.PrivilegeDTO;
import com.softserve.edu.Resources.security.entity.GenericPrivilege;
import com.softserve.edu.Resources.security.entity.ResourcePropertyPrivilege;
import com.softserve.edu.Resources.security.entity.ResourceTypePrivilege;
import com.softserve.edu.Resources.security.entity.SystemPrivilege;

import java.util.List;

public interface GenericPrivilegeService {
    GenericPrivilege addGenericPrivilege(GenericPrivilege privilege);

    GenericPrivilege updateGenericPrivilege(GenericPrivilege privilege);

    List<SystemPrivilege> getAllSystemPrivileges();

    List<ResourceTypePrivilege> getAllResourceTypePrivileges();

    List<ResourcePropertyPrivilege> getAllResourcePropertiesPrivileges();

    List<GenericPrivilege> getAllPrivileges();

    GenericPrivilege getPrivilegeById(long id);

    public GenericPrivilege convertToPrivilege(PrivilegeDTO privilegeDTO);

    //SelectInfoDTO fromAddressToDto(GenericPrivilege owner);
}
