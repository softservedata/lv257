package com.softserve.edu.Resources.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softserve.edu.Resources.entity.Address;
import com.softserve.edu.Resources.entity.Company;
import com.softserve.edu.Resources.entity.Owner;
import com.softserve.edu.Resources.entity.Person;
import com.softserve.edu.Resources.service.AddressService;
import com.softserve.edu.Resources.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Arrays;

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
        Address address = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            address = objectMapper.readValue(json, Address.class);
            addressService.addAddress(address);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (address == null) {
            return "fail";
        }
        System.out.println(address);
        return "";
    }

    @ResponseBody
    @RequestMapping(value = "/owner", method = RequestMethod.POST)
    public String saveResourceOwner(@RequestBody String json) {
        Address ownerAddress = null;
        Owner owner = null;

        ObjectMapper objectMapper = new ObjectMapper();

        String[] allOwnerInfo = json.split("\\|");
        System.out.println(json);
        System.out.println(Arrays.toString(allOwnerInfo));
        String addressJson = allOwnerInfo[0];
        System.out.println("address json: "  + addressJson);
        String ownerJson = allOwnerInfo[1];
        System.out.println("owner json: "  + ownerJson);
        String ownerType = allOwnerInfo[2];
        System.out.println("owner type: "  + ownerType);

        try {
            if (Integer.parseInt(ownerType) == 2) {
                owner = objectMapper.readValue(ownerJson, Company.class);
            } else {
                owner = objectMapper.readValue(ownerJson, Person.class);
            }
            ownerAddress = objectMapper.readValue(addressJson, Address.class);

            System.out.println(owner);
            System.out.println(ownerAddress);

            ownerAddress.addOwner(owner);
            owner.setAddress(ownerAddress);

            ownerService.addOwner(owner);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

}
