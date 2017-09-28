package com.softserve.edu.Resources.service.impl;

import com.softserve.edu.Resources.dao.AddressDAO;
import com.softserve.edu.Resources.dto.SelectInfoDTO;
import com.softserve.edu.Resources.entity.Address;
import com.softserve.edu.Resources.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Address getById(long id) {
        return addressDAO.getById(id);
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
    public SelectInfoDTO fromAddressToDto(Address address) {
        SelectInfoDTO infoDTO = new SelectInfoDTO();
        infoDTO.setObjectId(address.getId());
        infoDTO.setMessage(address.customToString());

        return infoDTO;
    }
}
