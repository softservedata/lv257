package com.softserve.edu.Resources.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softserve.edu.Resources.dao.AddressDAO;
import com.softserve.edu.Resources.entity.Address;
import com.softserve.edu.Resources.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {

    @Autowired
    AddressDAO addressDAO;

    @Override
    public void addAddress(Address address) {
        addressDAO.addAddress(address);
    }

    @Override
    public Address findById(long id) {
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

    @Override
    public Address parseAddress(String json) {
        Address address = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            address = objectMapper.readValue(json, Address.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return address;
    }
}
