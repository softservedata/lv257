package com.softserve.edu.Resources.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.softserve.edu.Resources.dto.FieldErrorDTO;
import com.softserve.edu.Resources.dto.OwnerDTO;
import com.softserve.edu.Resources.dto.SearchOwnerDTO;
import com.softserve.edu.Resources.dto.ValidationErrorDTO;
import com.softserve.edu.Resources.entity.Address;
import com.softserve.edu.Resources.entity.Owner;
import com.softserve.edu.Resources.entity.Person;
import com.softserve.edu.Resources.service.AddressService;
import com.softserve.edu.Resources.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/resources")
public class RegisterResourceController {

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
        System.out.println("Saving address: " + address);

        if (result.hasErrors()){
            ValidationErrorDTO validationErrorDTO = addressService.validationDTO(result);

            return new ResponseEntity<>(validationErrorDTO, HttpStatus.BAD_REQUEST);
        }
        Address savedAddress = addressService.addAddress(address);
//        SelectInfoDTO infoDTO = addressService.fromAddressToDto(address);

        return new ResponseEntity<>(savedAddress, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/address/update", method = RequestMethod.POST)
    public ResponseEntity<?> updateResourceAddress(@RequestBody @Valid Address address, BindingResult result) {
        System.out.println("Updating address: " + address);

        if (result.hasErrors()){
            ValidationErrorDTO validationErrorDTO = addressService.validationDTO(result);

            return new ResponseEntity<>(validationErrorDTO, HttpStatus.BAD_REQUEST);
        }
        Address savedAddress = addressService.updateAddress(address);
//        SelectInfoDTO infoDTO = addressService.fromAddressToDto(address);

        return new ResponseEntity<>(savedAddress, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/address/delete", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteResourceAddress(@RequestBody Address address) {
        System.out.println("Deleting address: " + address);

        addressService.deleteAddress(address);

        return new ResponseEntity<>(new Address(), HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/owner", method = RequestMethod.POST)
    public ResponseEntity<?> saveResourceOwnerWithAddress(@RequestBody @Valid Owner owner,
                                                          BindingResult result){
        if (result.hasErrors()){
            ValidationErrorDTO validationErrorDTO = ownerService.validationDTO(result);

            return new ResponseEntity<>(validationErrorDTO, HttpStatus.BAD_REQUEST);
        }
        Owner savedOwner = ownerService.addOwner(owner);

        System.out.println("Saved owner: " + savedOwner);
        System.out.println("Saved owner id: " + savedOwner.getId());
        System.out.println("Saved owner's address: " + savedOwner.getAddress());

        List<OwnerDTO> ownerDTOS = ownerService.fromOwnerToOwnerDto(Collections.singletonList(savedOwner));

        return new ResponseEntity<>(ownerDTOS.get(0), HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/owner/{id}/delete", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteOwner(@PathVariable long id) throws JsonProcessingException {
        System.out.println("Deleting owner with id:" + id);

        ownerService.deleteOwnerById(id);

        return new ResponseEntity<>(new Person(), HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/owner/search", method = RequestMethod.POST)
    public ResponseEntity<?> searchOwner(@RequestBody SearchOwnerDTO searchOwnerDTO){

        List<Owner> owners = ownerService.findOwners(searchOwnerDTO);

        if (owners.isEmpty()){
            System.out.println("Owner was not found");
            return new ResponseEntity<>(new FieldErrorDTO("errors", "Nothing was found."), HttpStatus.BAD_REQUEST);
        }

        List<OwnerDTO> ownerDTOS = ownerService.fromOwnerToOwnerDto(owners);

        return new ResponseEntity<>(ownerDTOS, HttpStatus.OK);
    }

}
