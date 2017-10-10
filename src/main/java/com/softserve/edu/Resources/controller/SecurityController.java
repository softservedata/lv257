package com.softserve.edu.Resources.controller;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/privilege")
@CrossOrigin
public class SecurityController {


    /*@RequestMapping(value = "/", method = RequestMethod.POST)
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

        System.out.println(allPrivilegesDTO.toString());
        return new ResponseEntity<> (allPrivilegesDTO, HttpStatus.OK);
    }*/
    
}
