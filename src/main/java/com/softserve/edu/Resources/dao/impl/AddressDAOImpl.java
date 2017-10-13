package com.softserve.edu.Resources.dao.impl;

import com.softserve.edu.Resources.dao.AddressDAO;
import com.softserve.edu.Resources.entity.Address;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AddressDAOImpl extends GenericDAOImpl<Address, Long> implements AddressDAO {

    public AddressDAOImpl() {
        super(Address.class);
    }

    @Override
    public List<Address> findAddresses(String query) {
        List resultList = em.createQuery(query).getResultList();

        return (List<Address>)resultList;
    }

    @Override
    public void deleteAddress(Address address) {
        em.remove(em.contains(address) ? address : em.merge(address));
    }
}
