package com.softserve.edu.Resources.service.impl;

import com.softserve.edu.Resources.dao.AddressDAO;
import com.softserve.edu.Resources.entity.Address;
import com.softserve.edu.Resources.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    AddressDAO addressDAO;

    @Override
    public void addAddress(Address address) {
        addressDAO.addAddress(address);
    }

    @Override
    public Address findById(int id) {
        return addressDAO.findById(id);
    }

    @Override
    public void updateAddress(Address address) {
        addressDAO.updateAddress(address);
    }

    @Override
    public List<Address> getAllAddresses() {
        return addressDAO.getAllAddresses();
    }
}
