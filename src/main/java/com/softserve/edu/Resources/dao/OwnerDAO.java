package com.softserve.edu.Resources.dao;

import com.softserve.edu.Resources.entity.Owner;

import java.util.List;

public interface OwnerDAO {

    void addOwner(Owner owner);

    void updateOwner(Owner owner);

    List<Owner> getAllCompanies();

    List<Owner> getAllPersons();

    List<Owner> getAllOwners();

    Owner getOwnerById(long id);

    List<Owner> findOwners(String query);

}
