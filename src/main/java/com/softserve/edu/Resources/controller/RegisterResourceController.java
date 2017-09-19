package com.softserve.edu.Resources.controller;

import com.softserve.edu.Resources.entity.Address;
import com.softserve.edu.Resources.entity.Owner;
import com.softserve.edu.Resources.service.AddressService;
import com.softserve.edu.Resources.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public String saveResourceAddress(@RequestBody String json) {
        Address address;
        address = addressService.parseAddress(json);
        addressService.addAddress(address);

        return "";
    }

    @ResponseBody
    @RequestMapping(value = "/owner", method = RequestMethod.POST)
    public String saveResourceOwnerWithAddress(@RequestBody String json) {
        Owner owner;
        owner = ownerService.parseOwnerWithAddress(json);
        ownerService.addOwner(owner);

        return "";
    }

}
