package com.softserve.edu.Resources.dao.impl;

import com.softserve.edu.Resources.dao.AddressDAO;
import com.softserve.edu.Resources.entity.Address;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class AddressDAOImpl extends GenericDAOImpl<Address, Long> implements AddressDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public AddressDAOImpl() {
        super(Address.class);
    }

    @Override
    public void addAddress(Address address) {
        this.makePersistent(address);
    }

    @Override
    public Address getById(long id) {
        return this.findById(id).get();
    }

    @Override
    public void updateAddress(Address address) {
        this.merge(address);
    }

    @Override
    public List<Address> getAllAddresses() {
        return this.findAll();  
    }
}
