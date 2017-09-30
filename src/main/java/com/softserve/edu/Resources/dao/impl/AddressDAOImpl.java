package com.softserve.edu.Resources.dao.impl;

import com.softserve.edu.Resources.dao.AddressDAO;
import com.softserve.edu.Resources.entity.Address;
import com.softserve.edu.Resources.entity.Owner;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class AddressDAOImpl extends GenericDAOImpl<Address, Long> implements AddressDAO {

//    @PersistenceContext
//    private EntityManager entityManager;

    public AddressDAOImpl() {
        super(Address.class);
    }

    @Override
    public Address addAddress(Address address) {
        Address savedAddress = this.makePersistent(address);
        return savedAddress;
    }

    @Override
    public Address getById(long id) {
        return this.findById(id).orElse(new Address());
    }

    @Override
    public Address updateAddress(Address address) {
        Address updatedAddress = this.makePersistent(address);
        return updatedAddress;
    }

    @Override
    public List<Address> getAllAddresses() {
        return this.findAll();  
    }

    @Override
    public void deleteAddress(Address address) {
        Query query = em.createQuery("DELETE FROM Address WHERE id= :id");
        query.setParameter("id", address.getId());
        query.executeUpdate();
    }
}
