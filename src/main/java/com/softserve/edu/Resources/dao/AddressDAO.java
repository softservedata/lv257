package com.softserve.edu.Resources.dao;

import com.softserve.edu.Resources.entity.Address;

import java.util.List;

public interface AddressDAO {

    Address addAddress(Address address);

    Address getById(long id);

    Address updateAddress(Address address);

    List<Address> getAllAddresses();

    void deleteAddress(Address address);
}
