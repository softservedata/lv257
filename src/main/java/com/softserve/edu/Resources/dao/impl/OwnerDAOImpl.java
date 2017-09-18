package com.softserve.edu.Resources.dao.impl;

import com.softserve.edu.Resources.dao.OwnerDAO;
import com.softserve.edu.Resources.entity.Owner;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class OwnerDAOImpl implements OwnerDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addOwner(Owner owner) {
        entityManager.persist(owner);
    }

    @Override
    public void updateOwner(Owner owner) {
        entityManager.merge(owner);
    }

    @Override
    public List<Owner> getAllCompanies() {
        Query query = entityManager.createQuery("SELECT c FROM Company c");
        return (List<Owner>) query.getResultList();
    }

    @Override
    public List<Owner> getAllPersons() {
        Query query = entityManager.createQuery("SELECT p FROM Person p");
        return (List<Owner>) query.getResultList();
    }

    @Override
    public List<Owner> getAllOwners() {
        Query query = entityManager.createQuery("SELECT o FROM Owner o");
        return (List<Owner>) query.getResultList();
    }

    @Override
    public Owner getOwnerById(long id) {
        Query query = entityManager.createQuery("SELECT o FROM Owner o WHERE id = :id");
        query.setParameter("id", id);
        return (Owner) query.getSingleResult();
    }

}
