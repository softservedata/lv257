package edu.softserve.service;


import edu.softserve.dto.UserDTO;
import edu.softserve.entity.User;

import java.util.List;

public interface UserService {

    public User getUserForSpring (String email);

    public User getUserById (Long id);

    public List<User> getAllUsers();

    public User registerNewUserAccount(final UserDTO userDTO);
}
