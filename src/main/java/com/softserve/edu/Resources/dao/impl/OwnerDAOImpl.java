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

@Repository
public class OwnerDAOImpl extends GenericDAOImpl<Owner, Long> implements OwnerDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public OwnerDAOImpl() {
        super(Owner.class);
    }

    @Override
    public Owner addOwner(Owner owner) {
        return this.makePersistent(owner);
    }

    @Override
    public void updateOwner(Owner owner) {
        this.makePersistent(owner);
    }

    @Override
    public void deleteOwnerById(Long id) {
        Query query = em.createQuery("DELETE FROM Owner WHERE id= :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public List<Owner> getAllCompanies() {
        Query query = entityManager.createQuery("SELECT c FROM Company c");
        return (List<Owner>) query.getResultList();

//        return this.findAll().stream()
//                            .filter(x -> x.getClass().equals(Company.class))
//                            .collect(Collectors.toList());
    }

    @Override
    public List<Owner> getAllPersons() {
        Query query = entityManager.createQuery("SELECT p FROM Person p");
        return (List<Owner>) query.getResultList();

//        return this.findAll().stream()
//                .filter(x -> x.getClass().equals(Person.class))
//                .collect(Collectors.toList());
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

    @Override
    public List<Owner> findOwners(String query) {
        List resultList = em.createQuery(query).getResultList();
        System.out.println(resultList);

        return (List<Owner>)resultList;
    }
}
