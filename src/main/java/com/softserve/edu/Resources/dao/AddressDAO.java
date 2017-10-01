package com.softserve.edu.Resources.dao;

import com.softserve.edu.Resources.entity.Address;

public interface AddressDAO extends GenericDAO<Address, Long> {

    void deleteAddress(Address address);
}
