package com.softserve.edu.Resources.dao.impl;

import com.softserve.edu.Resources.dao.OwnerDAO;
import com.softserve.edu.Resources.dto.SearchOwnerDTO;
import com.softserve.edu.Resources.entity.Owner;
import com.softserve.edu.Resources.entity.Person;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class OwnerDAOImpl extends GenericDAOImpl<Owner, Long> implements OwnerDAO {

    public OwnerDAOImpl() {
        super(Owner.class);
    }

    @Override
    public void deleteOwnerById(Long id) {
        Owner ownerToDelete = findById(id).get();
        em.remove(em.contains(ownerToDelete) ? ownerToDelete : em.merge(ownerToDelete));
    }

    @Override
    public List<Owner> getAllCompanies() {
        Query query = em.createQuery("SELECT c FROM Company c");
        return (List<Owner>) query.getResultList();
    }

    @Override
    public List<Owner> getAllPersons() {
        Query query = em.createQuery("SELECT p FROM Person p");
        return (List<Owner>) query.getResultList();
    }

    @Override
    public List<Owner> findOwners(String query) {
        List resultList = em.createQuery(query).getResultList();
        System.out.println(resultList);

        return (List<Owner>)resultList;
    }
}
