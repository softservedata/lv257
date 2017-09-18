package com.softserve.edu.Resources.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softserve.edu.Resources.entity.Address;
import com.softserve.edu.Resources.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
@RequestMapping("/resources")
public class RegisterResourceController {

    @Autowired
    AddressService addressService;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registerResource(){
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
        if (address == null){
            return "fail";
        }
        System.out.println(address);
        return "";
    }
}
