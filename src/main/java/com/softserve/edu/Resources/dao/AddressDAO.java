package com.softserve.edu.Resources.dao;

import com.softserve.edu.Resources.entity.Address;

import java.util.List;

public interface AddressDAO {

    void addAddress(Address address);

    Address findById(int id);

    void updateAddress(Address address);

    List<Address> getAllAddresses();
}
