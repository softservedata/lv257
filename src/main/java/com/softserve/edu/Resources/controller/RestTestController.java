package com.softserve.edu.Resources.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.softserve.edu.Resources.dto.MessageTestRest;



@RestController
public class RestTestController {

    @RequestMapping("/demo")
    public String welcome() {//Welcome page, non-rest
        return "Welcome to RestTemplate Example.";
    }
 
    @RequestMapping("/hello/{player}")
    public MessageTestRest message(@PathVariable String player) {//REST Endpoint.
 
        MessageTestRest msg = new MessageTestRest(player, "Hello " + player);
        return msg;
    }
    
}
