package com.softserve.edu.Resources.service.impl;

import com.softserve.edu.Resources.dao.AddressDAO;
import com.softserve.edu.Resources.dto.ValidationErrorDTO;
import com.softserve.edu.Resources.entity.Address;
import com.softserve.edu.Resources.util.ValidationDTOUtility;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.BindingResult;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AddressServiceImplTest {

    private static long addressId;
    private static long nonExistingAddressId;
    private static Address transientOrEmptyAddress;
    private static Address persistentAddress;
    private static List<Address> allAddresses;
    private static Optional findByIdAddress;
    private static ValidationErrorDTO validationErrorDTO;

    @Mock
    private AddressDAO addressDAO;

    @Mock
    private ValidationDTOUtility utility;

    @InjectMocks
    private AddressServiceImpl addressServiceImpl;

    @BeforeClass
    public static void setUp() {
        addressId = 5L;
        nonExistingAddressId = 105L;
        transientOrEmptyAddress = new Address();
        persistentAddress = new Address()
                .setId(addressId)
                .setCountry("Ukraine")
                .setRegion("Lvivsky");
        allAddresses = Arrays.asList(new Address(), new Address());
        findByIdAddress = Optional.ofNullable(transientOrEmptyAddress);
        validationErrorDTO = new ValidationErrorDTO();
    }

    @Test
    public void saveAddress() {
        when(addressDAO.makePersistent(any(Address.class))).thenReturn(persistentAddress);

        Address savedAddress = addressServiceImpl.addAddress(transientOrEmptyAddress);

        assertEquals(persistentAddress, savedAddress);
        assertEquals(savedAddress.getId(), addressId);
        verify(addressDAO).makePersistent(transientOrEmptyAddress);
    }

    @Test
    public void getById() {
        when(addressDAO.findById(addressId)).thenReturn(findByIdAddress);

        assertEquals(addressServiceImpl.getById(addressId), findByIdAddress.get());
        verify(addressDAO, times(1)).findById(addressId);
    }

    @Test
    public void getByIdNull() {
        Optional<Address> emptyAddress = Optional.ofNullable(new Address());
        when(addressDAO.findById(nonExistingAddressId)).thenReturn(emptyAddress);

        assertEquals(addressServiceImpl.getById(nonExistingAddressId), emptyAddress.get());
        verify(addressDAO, times(1)).findById(nonExistingAddressId);
    }

    @Test
    public void updateAddress() {
        addressServiceImpl.updateAddress(persistentAddress);

        verify(addressDAO, times(1)).makePersistent(persistentAddress);
    }

    @Test
    public void deleteAddress(){
        addressServiceImpl.deleteAddress(persistentAddress);

        verify(addressDAO, times(1)).deleteAddress(persistentAddress);
    }

    @Test
    public void getAllAddresses(){
        when(addressDAO.findAll()).thenReturn(allAddresses);

        List<Address> allAddressesFromDB = addressServiceImpl.getAllAddresses();

        assertEquals(allAddresses, allAddressesFromDB);
        verify(addressDAO, times(1)).findAll();
    }

    @Test
    public void validationDTOTest(){
        when(utility.getErrorDTO(any(BindingResult.class))).thenReturn(validationErrorDTO);

        ValidationErrorDTO resultValidationErrorDTO = addressServiceImpl.validationDTO(any(BindingResult.class));

        assertEquals(resultValidationErrorDTO, validationErrorDTO);
        verify(utility, times(1)).getErrorDTO(any(BindingResult.class));
    }

}
