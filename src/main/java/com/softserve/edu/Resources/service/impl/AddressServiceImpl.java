package com.softserve.edu.Resources.service.impl;

import com.softserve.edu.Resources.dao.AddressDAO;
import com.softserve.edu.Resources.dto.ValidationErrorDTO;
import com.softserve.edu.Resources.entity.Address;
import com.softserve.edu.Resources.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {

    @Autowired
    AddressDAO addressDAO;

    @Override
    public Address addAddress(Address address) {
        return addressDAO.makePersistent(address);
    }

    @Override
    public Address getById(long id) {
        return addressDAO.findById(id).orElse(new Address());
    }

    @Override
    public Address updateAddress(Address address) {
        return addressDAO.makePersistent(address);
    }

    @Override
    public void deleteAddress(Address address) {
        addressDAO.deleteAddress(address);
    }

    @Override
    public List<Address> getAllAddresses() {
        return addressDAO.findAll();
    }

    @Override
    public ValidationErrorDTO validationDTO(BindingResult result) {
        ValidationErrorDTO validationErrorDTO = new ValidationErrorDTO();

        List<FieldError> fieldErrors = result.getFieldErrors();
        fieldErrors.forEach(error -> validationErrorDTO.addFieldError(error.getField(), error.getDefaultMessage()));

        fieldErrors.forEach(System.out::println);
        System.out.println("has errors");

        return validationErrorDTO;
    }
}
