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

    public AddressDAOImpl() {
        super(Address.class);
    }

    @Override
    public void deleteAddress(Address address) {
        Query query = em.createQuery("DELETE FROM Address WHERE id= :id");
        query.setParameter("id", address.getId());
        query.executeUpdate();
    }
}
