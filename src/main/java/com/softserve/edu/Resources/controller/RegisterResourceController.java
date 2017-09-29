package com.softserve.edu.Resources.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.softserve.edu.Resources.dto.FieldErrorDTO;
import com.softserve.edu.Resources.dto.SearchOwnerDTO;
import com.softserve.edu.Resources.dto.SelectInfoDTO;
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
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.ArrayList;
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
    public ResponseEntity<?> saveResourceAddress(@RequestBody @Valid Address address,
                                                 BindingResult result) {
        System.out.println("Address obtained from client: " + address);

        ValidationErrorDTO validationErrorDTO = new ValidationErrorDTO();

        if (result.hasErrors()){
            List<FieldError> fieldErrors = result.getFieldErrors();
            fieldErrors.forEach(error -> validationErrorDTO.addFieldError(error.getField(), error.getDefaultMessage()));

            fieldErrors.forEach(System.out::println);

            System.out.println("has errors");
            return new ResponseEntity<>(validationErrorDTO, HttpStatus.BAD_REQUEST);
        }
        addressService.addAddress(address);

        SelectInfoDTO infoDTO = addressService.fromAddressToDto(address);

        return new ResponseEntity<>(infoDTO, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/owner", method = RequestMethod.POST)
    public ResponseEntity<?> saveResourceOwnerWithAddress(@RequestBody @Valid Owner owner,
                                                          BindingResult result) throws JsonProcessingException {

        ValidationErrorDTO validationErrorDTO = new ValidationErrorDTO();

        if (result.hasErrors()){
            List<FieldError> fieldErrors = result.getFieldErrors();
            fieldErrors.forEach(error -> validationErrorDTO.addFieldError(error.getField(), error.getDefaultMessage()));

            fieldErrors.forEach(System.out::println);

            System.out.println("has errors!!!");
            return new ResponseEntity<>(validationErrorDTO, HttpStatus.BAD_REQUEST);
        }
        ownerService.addOwner(owner);

        System.out.println(owner);
        System.out.println(owner.getAddress());

        SelectInfoDTO infoDTO = ownerService.fromOwnerToDto(owner);

        return new ResponseEntity<>(infoDTO, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/owner/search", method = RequestMethod.POST)
    public ResponseEntity<?> saveResourceOwnerWithAddress(@RequestBody SearchOwnerDTO searchOwnerDTO){

        List<Owner> owners = ownerService.findOwners(searchOwnerDTO);
        List<SelectInfoDTO> ownerSelects = new ArrayList<>();

        if (owners.isEmpty()){
            System.out.println("Owner was not found");
            return new ResponseEntity<>(new FieldErrorDTO("erros", "Nothing was found."), HttpStatus.BAD_REQUEST);
        }

        owners.forEach(owner -> ownerSelects.add(new SelectInfoDTO(owner.getId(), owner.customToString())));
        System.out.println(ownerSelects);

        return new ResponseEntity<>(ownerSelects, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/persons", method = RequestMethod.GET)
    public ResponseEntity<?> saveResourceOwner() throws JsonProcessingException {
        System.out.println(ownerService.getAllPersons());


        return new ResponseEntity<>(new Person(), HttpStatus.OK);
    }

}
