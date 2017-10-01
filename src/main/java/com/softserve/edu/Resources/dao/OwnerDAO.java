package com.softserve.edu.Resources.dao;

import com.softserve.edu.Resources.entity.Owner;

import java.util.List;

public interface OwnerDAO extends GenericDAO<Owner, Long>{

    void deleteOwnerById(Long id);

    List<Owner> getAllCompanies();

    List<Owner> getAllPersons();

    List<Owner> findOwners(String query);

}
