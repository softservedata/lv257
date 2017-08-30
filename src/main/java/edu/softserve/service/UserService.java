package edu.softserve.service;

import edu.softserve.dao.RoleDAO;
import edu.softserve.dao.UserDAO;
import edu.softserve.entity.Privilege;
import edu.softserve.entity.Role;
import edu.softserve.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserService {

    @Autowired
    UserDAO userDAO;
    @Autowired
    RoleDAO roleDAO;


    @Transactional
    public User getUserForSpring (String email){
        System.out.println("Userservice HALoo");
        User user = userDAO.findByEmail(email);
        System.out.println(user.toString());
        System.out.println("User extracted");
       Role role = user.getRole();
        System.out.println("Role extracted");
        ArrayList<Privilege> privileges = new ArrayList<>(role.getPrivileges());
        System.out.println("Privileges extracted");
        return user;
    }
}
