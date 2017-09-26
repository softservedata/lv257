package com.softserve.edu.Resources.controller;

import com.softserve.edu.Resources.dto.SelectInfoDTO;
import com.softserve.edu.Resources.dto.ValidationErrorDTO;
import com.softserve.edu.Resources.entity.Address;
import com.softserve.edu.Resources.entity.Owner;
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

    @RequestMapping(value = "/owner", method = RequestMethod.POST)
    public String saveResourceOwnerWithAddress(@RequestBody String json) {
        Owner owner;
        owner = ownerService.parseOwnerWithAddress(json);
        ownerService.addOwner(owner);

        System.out.println(owner.getId());
        return "" + owner.getId();
    }

}
