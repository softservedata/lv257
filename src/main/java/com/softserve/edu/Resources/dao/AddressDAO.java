package com.softserve.edu.Resources.dao;

import com.softserve.edu.Resources.entity.Address;

import java.util.List;

public interface AddressDAO extends GenericDAO<Address, Long> {

    void deleteAddress(Address address);

    List<Address> findAddresses(String query);

}
