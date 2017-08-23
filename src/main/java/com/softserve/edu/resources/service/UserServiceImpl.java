package com.softserve.edu.resources.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.edu.resources.dao.UserDao;
import com.softserve.edu.resources.entity.User;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    
    @Transactional
    public void save() {
        userDao.save(new User("Test2"));
    }

}
