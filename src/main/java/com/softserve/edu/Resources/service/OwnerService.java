package com.softserve.edu.Resources.service;

import com.softserve.edu.Resources.dto.SelectInfoDTO;
import com.softserve.edu.Resources.entity.Owner;

import java.util.List;

public interface OwnerService {

    void addOwner(Owner owner);

    void updateOwner(Owner owner);

    List<Owner> getAllCompanies();

    List<Owner> getAllPersons();

    List<Owner> getAllOwners();

    Owner getOwnerById(long id);

    SelectInfoDTO fromAddressToDto(Owner owner);

}
