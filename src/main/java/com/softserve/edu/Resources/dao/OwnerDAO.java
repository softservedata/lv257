package com.softserve.edu.Resources.dao;

import com.softserve.edu.Resources.entity.Owner;

import java.util.List;

public interface OwnerDAO {

    Owner addOwner(Owner owner);

    void updateOwner(Owner owner);

    void deleteOwnerById(Long id);

    List<Owner> getAllCompanies();

    List<Owner> getAllPersons();

    List<Owner> getAllOwners();

    Owner getOwnerById(long id);

    List<Owner> findOwners(String query);

}
