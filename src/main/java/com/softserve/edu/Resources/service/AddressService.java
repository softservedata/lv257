package com.softserve.edu.Resources.service;

import com.softserve.edu.Resources.dto.SearchDTO;
import com.softserve.edu.Resources.dto.ValidationErrorDTO;
import com.softserve.edu.Resources.entity.Address;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface AddressService {

    Address addAddress(Address address);

    Address getById(long id);

    Address updateAddress(Address address);

    void deleteAddress(Address address);

    List<Address> getAllAddresses();

    List<Address> findAddresses(SearchDTO searchDTO);

    ValidationErrorDTO validationDTO(BindingResult result);
}
