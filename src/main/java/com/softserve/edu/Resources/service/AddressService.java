package com.softserve.edu.Resources.service;

import com.softserve.edu.Resources.entity.Address;

import java.util.List;

public interface AddressService {

    void addAddress(Address address);

    Address findById(long id);

    void updateAddress(Address address);

    List<Address> getAllAddresses();
}
