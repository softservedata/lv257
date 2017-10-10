package com.softserve.edu.Resources.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.softserve.edu.Resources.dto.FieldErrorDTO;
import com.softserve.edu.Resources.dto.OwnerDTO;
import com.softserve.edu.Resources.dto.SearchDTO;
import com.softserve.edu.Resources.dto.ValidationErrorDTO;
import com.softserve.edu.Resources.entity.Address;
import com.softserve.edu.Resources.entity.Owner;
import com.softserve.edu.Resources.service.AddressService;
import com.softserve.edu.Resources.service.OwnerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.invoke.MethodHandles;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/resources")
public class RegisterResourceController {

    static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass().getName());

    @Autowired
    AddressService addressService;

    @Autowired
    OwnerService ownerService;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registerResource() {
        return "registerResource";
    }

    @ResponseBody
    @RequestMapping(value = "/address", method = RequestMethod.POST)
    public ResponseEntity<?> saveResourceAddress(@RequestBody @Valid Address address, BindingResult result) {
        logger.info("Saving address: " + address);

        if (result.hasErrors()){
            logger.warn("Errors in address object!");

            return new ResponseEntity<>(addressService.validationDTO(result), HttpStatus.BAD_REQUEST);
        }
        Address savedAddress = addressService.addAddress(address);

        logger.debug("Saved address in the bd: " + savedAddress);

        return new ResponseEntity<>(savedAddress, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/address/delete", method = RequestMethod.DELETE)
    public void deleteResourceAddress(@RequestBody Address address) {
        logger.debug("Deleting address: " + address);

        addressService.deleteAddress(address);

        logger.warn("Address deleted from the db" + address);
    }

    @ResponseBody
    @RequestMapping(value = "/owner", method = RequestMethod.POST)
    public ResponseEntity<?> saveResourceOwnerWithAddress(@RequestBody @Valid Owner owner, BindingResult result){
        logger.info("Saving owner: " + owner);
        System.out.println("Saving owner: " + owner);
        System.out.println("Saving owner's address: " + owner.getAddress());

        if (result.hasErrors()){
            logger.warn("Errors in the owner object!");
            ValidationErrorDTO validationErrorDTO = ownerService.validationDTO(result);
            System.out.println(validationErrorDTO.getFieldErrors());

            return new ResponseEntity<>(validationErrorDTO, HttpStatus.BAD_REQUEST);
        }

        Owner savedOwner = ownerService.addOwner(owner);
        savedOwner.getAddress().addOwner(savedOwner);
        addressService.updateAddress(savedOwner.getAddress());

        logger.debug("Saved owner: " + savedOwner);
        logger.debug("Saved owner id: " + savedOwner.getId());
        logger.debug("Saved owner's address: " + savedOwner.getAddress());

        List<OwnerDTO> ownerDTOS = ownerService.fromOwnerToOwnerDto(Collections.singletonList(savedOwner));

        return new ResponseEntity<>(ownerDTOS.get(0), HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/owner/{id}/delete", method = RequestMethod.DELETE)
    public void deleteOwner(@PathVariable long id) throws JsonProcessingException {
        logger.warn("Deleting owner with id:" + id);

        ownerService.deleteOwnerById(id);
    }

    @ResponseBody
    @RequestMapping(value = "/owner/search", method = RequestMethod.POST)
    public ResponseEntity<?> searchOwner(@RequestBody SearchDTO searchDTO){
        logger.debug("Searching owner with values: " + searchDTO.getFieldsAndValues().values());

        List<Owner> owners = ownerService.findOwners(searchDTO);

        if (owners.isEmpty()){
            logger.warn("Owner was not found!");

            return new ResponseEntity<>(new FieldErrorDTO("errors", "Nothing was found. Please, try again."), HttpStatus.BAD_REQUEST);
        }

        List<OwnerDTO> ownerDTOS = ownerService.fromOwnerToOwnerDto(owners);

        return new ResponseEntity<>(ownerDTOS, HttpStatus.OK);
    }


    @ResponseBody
    @RequestMapping(value = "/address/search", method = RequestMethod.POST)
    public ResponseEntity<?> searchAddress(@RequestBody SearchDTO searchDTO){
        logger.debug("Searching address with values: " + searchDTO.getFieldsAndValues().values());

        List<Address> addresses = addressService.findAddresses(searchDTO);

        if (addresses.isEmpty()){
            logger.warn("Address was not found!");

            return new ResponseEntity<>(new FieldErrorDTO("errors", "Nothing was found. Please, try again."), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }

}
