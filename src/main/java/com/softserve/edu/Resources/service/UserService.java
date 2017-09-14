package com.softserve.edu.Resources.service;


import com.softserve.edu.Resources.dto.UserDTO;
import com.softserve.edu.Resources.entity.User;

import java.util.List;

public interface UserService {

    public User getUserForSpring (String email);

    public User getUserById (Long id);

    public List<User> getAllUsers();

    public User registerNewUserAccount(final UserDTO userDTO);
}
