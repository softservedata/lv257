package com.softserve.edu.Resources.controller;

import com.softserve.edu.Resources.security.dto.AllPrivilegesDTO;
import com.softserve.edu.Resources.security.dto.PrivilegeDTO;
import com.softserve.edu.Resources.security.dto.SystemPrivilegeDTO;
import com.softserve.edu.Resources.security.entity.GenericPrivilege;
import com.softserve.edu.Resources.security.entity.ResourceTypePrivilege;
import com.softserve.edu.Resources.security.entity.SystemPrivilege;
import com.softserve.edu.Resources.security.service.GenericPrivilegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/privilege")
public class SecurityController {

    @Autowired
    GenericPrivilegeService genericPrivilegeService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public GenericPrivilege addResourceTypePrivilege (@RequestBody PrivilegeDTO privilegeDTO) {

        if (privilegeDTO == null) {
            System.out.println("is null");
            return  new SystemPrivilege();
        }
        GenericPrivilege privilege = genericPrivilegeService.convertToPrivilege(privilegeDTO);
        privilege = genericPrivilegeService.addGenericPrivilege(privilege);
        return privilege;

    }

    @RequestMapping(value = "/", method = RequestMethod.GET, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<GenericPrivilege> get() {

        GenericPrivilege privilege = new ResourceTypePrivilege();
        //privilege = (ResourceTypePrivilege)privilege;

        privilege.setName("blaa");

        System.out.println(privilege.toString());
        genericPrivilegeService.addGenericPrivilege(privilege);
        return new ResponseEntity<> (privilege, HttpStatus.OK);
    }

    @RequestMapping(value = "/system", method = RequestMethod.GET, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<SystemPrivilegeDTO> getAllSystemPrivileges() {

        List<SystemPrivilege> systemPrivileges = genericPrivilegeService.getAllSystemPrivileges();

        SystemPrivilegeDTO systemPrivilegeDTO = new SystemPrivilegeDTO();
        systemPrivilegeDTO.setSystemPrivileges(systemPrivileges);

        System.out.println(systemPrivilegeDTO);

        return new ResponseEntity<> (systemPrivilegeDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<AllPrivilegesDTO> getAllPrivileges() {
        AllPrivilegesDTO allPrivilegesDTO = new AllPrivilegesDTO();
        allPrivilegesDTO.setSystemPrivileges(genericPrivilegeService.getAllSystemPrivileges());
        allPrivilegesDTO.setResourceTypePrivileges(genericPrivilegeService.getAllResourceTypePrivileges());
        allPrivilegesDTO.setResourcePropertyPrivileges(genericPrivilegeService.getAllResourcePropertiesPrivileges());

        return new ResponseEntity<> (allPrivilegesDTO, HttpStatus.OK);
    }

    /*@RequestMapping("/hello/{player}")
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
    }*/
    
}
