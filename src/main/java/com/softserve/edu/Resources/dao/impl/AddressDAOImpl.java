package com.softserve.edu.Resources.dao.impl;

import com.softserve.edu.Resources.dao.AddressDAO;
import com.softserve.edu.Resources.entity.Address;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class AddressDAOImpl implements AddressDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public void addAddress(Address address) {
        entityManager.persist(address);
    }

    public Address findById(int id) {
        Query query = entityManager.createQuery("SELECT a FROM Address a WHERE id = :id");
        query.setParameter("id", id);
        return (Address) query.getSingleResult();
    }

    public void updateAddress(Address address) {
        entityManager.merge(address);
    }

    public List<Address> getAllAddresses() {
        Query query = entityManager.createQuery("SELECT a FROM Address a");
        return (List<Address>) query.getResultList();
    }
}
