package com.softserve.edu.Resources.service.impl;

import com.softserve.edu.Resources.dao.AddressDAO;
import com.softserve.edu.Resources.dto.ValidationErrorDTO;
import com.softserve.edu.Resources.entity.Address;
import com.softserve.edu.Resources.service.AddressService;
import com.softserve.edu.Resources.util.ValidationDTOUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import java.lang.invoke.MethodHandles;
import java.util.List;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {

    static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass().getName());

    @Autowired
    AddressDAO addressDAO;

    @Autowired
    private ValidationDTOUtility validationUtility;

    @Override
    public Address addAddress(Address address) {
        logger.trace("Saving address:" + address);

        return addressDAO.makePersistent(address);
    }

    @Override
    public Address getById(long id) {
        logger.trace("Retrieving address:" + id);

        return addressDAO.findById(id).orElse(new Address());
    }

    @Override
    public Address updateAddress(Address address) {
        logger.trace("Updating address:" + address);

        return addressDAO.makePersistent(address);
    }

    @Override
    public void deleteAddress(Address address) {
        logger.trace("Deleting address:" + address);

        addressDAO.deleteAddress(address);
    }

    @Override
    public List<Address> getAllAddresses() {
        logger.trace("Retrieving all addresses");

        return addressDAO.findAll();
    }

    @Override
    public ValidationErrorDTO validationDTO(BindingResult result) {
        logger.trace("Validating address with errors in fields:" + result.getFieldErrors());

        return validationUtility.getErrorDTO(result);
    }
}
