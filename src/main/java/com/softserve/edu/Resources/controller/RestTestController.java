package com.softserve.edu.Resources.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.softserve.edu.Resources.dto.CarRestTest;
import com.softserve.edu.Resources.dto.GenericResourceDTO;
import com.softserve.edu.Resources.dto.MessageTestRest;
import com.softserve.edu.Resources.entity.GenericResource;
import com.softserve.edu.Resources.entity.PropertyValue;
import com.softserve.edu.Resources.entity.ResourceProperty;
import com.softserve.edu.Resources.entity.ValueType;



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
    
    @RequestMapping(value = "/updateCar", method = RequestMethod.POST)
    public CarRestTest update(@RequestBody CarRestTest car) {
        System.out.println(car.getColor());
        if (car != null){
            car.setColor("black");
        }
        return car;
    }
    
    @RequestMapping(value = "/updateGenRes", method = RequestMethod.POST)
    public GenericResourceDTO updater(@RequestBody GenericResourceDTO genRes) {
        System.out.println(genRes.getId());
        System.out.println(genRes.getResourcePropertyValue());
        if (genRes != null){
            genRes.setId(2);
        }
        return genRes;
    }
    
    @RequestMapping(value = "/showGenRes/{id}", method = RequestMethod.GET)
    public GenericResourceDTO get(@PathVariable int id) {
        
        Map<String, String> values = new TreeMap<>();
        values.put("Model", "Mazda");
        values.put("Year", "2005");
        
        GenericResourceDTO genRes = new GenericResourceDTO(id, values);
        
        return genRes;
    }
    
    @RequestMapping(value = "/showGenResList/{id}", method = RequestMethod.GET)
    public List <GenericResourceDTO> getListGen(@PathVariable int id) {
        
        Map<String, String> values = new TreeMap<>();
        values.put("Model", "Mazda");
        values.put("Year", "2005");
        
        GenericResourceDTO genRes = new GenericResourceDTO(id, values);
        GenericResourceDTO genRes2 = new GenericResourceDTO(id, values);
        List <GenericResourceDTO> listGenRes = new ArrayList<>();
        listGenRes.add(genRes);
        
        listGenRes.add(genRes2);
        
        return listGenRes;
    }
    
    @RequestMapping(value = "/showGenResListAll", method = RequestMethod.GET)
    public List <GenericResource> getListGenRes() {
        
        List <GenericResource> listGenRes = new ArrayList<>();
        GenericResource genRes1 = new GenericResource();
        genRes1.setId(1);
        genRes1.setId_Address(1);
        Set <PropertyValue> propertyValues = new HashSet<>();
       
        PropertyValue propValue1 = new PropertyValue();
        
        ResourceProperty resProp = new ResourceProperty();
        resProp.setId(1);
        resProp.setColumnName("Model");
        resProp.setValueType(ValueType.STRING);
        
        propValue1.setType(resProp);
        
        propertyValues.add(propValue1);
        
        genRes1.setPropertyValues(propertyValues);
        
        listGenRes.add(genRes1);
        
        return listGenRes;
    }
    
}
