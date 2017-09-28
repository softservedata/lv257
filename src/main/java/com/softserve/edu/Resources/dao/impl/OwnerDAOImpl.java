package com.softserve.edu.Resources.dao.impl;

import com.softserve.edu.Resources.dao.OwnerDAO;
import com.softserve.edu.Resources.entity.Company;
import com.softserve.edu.Resources.entity.Owner;
import com.softserve.edu.Resources.entity.Person;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class OwnerDAOImpl extends GenericDAOImpl<Owner, Long> implements OwnerDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public OwnerDAOImpl() {
        super(Owner.class);
    }

    @Override
    public void addOwner(Owner owner) {
        this.makePersistent(owner);
    }

    @Override
    public void updateOwner(Owner owner) {
        this.makePersistent(owner);
    }

    @Override
    public List<Owner> getAllCompanies() {
//        Query query = entityManager.createQuery("SELECT c FROM Company c");
//        return (List<Owner>) query.getResultList();

        return this.findAll().stream()
                            .filter(x -> x.getClass().equals(Company.class))
                            .collect(Collectors.toList());
    }

    @Override
    public List<Owner> getAllPersons() {
//        Query query = entityManager.createQuery("SELECT p FROM Person p");
//        return (List<Owner>) query.getResultList();

        return this.findAll().stream()
                .filter(x -> x.getClass().equals(Person.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<Owner> getAllOwners() {
        return this.findAll();
    }

    @Override
    public Owner getOwnerById(long id) {
//        Query query = entityManager.createQuery("SELECT o FROM Owner o WHERE id = :id");
//        query.setParameter("id", id);
//        return (Owner) query.getSingleResult();
        return this.findById(id).orElse(new Person());
    }

}
