package com.softserve.edu.Resources.service;

//import com.softserve.edu.Resources.dao.RoleDAO;
//import com.softserve.edu.Resources.dao.UserDetailsDAO;
import com.softserve.edu.Resources.entity.User;
import com.softserve.edu.Resources.entity.UserDetails;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserDetailsService {

    public UserDetails getUserDetailsByEmail(String email);

    public UserDetails getUserDetailsById (Long id);

    public List<User> getAllUsersDetails();
}
