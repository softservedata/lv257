package com.softserve.edu.resources.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "users")
//@NamedQueries({
//    @NamedQuery(name = User.FIND_BY_NAME, query = User.FIND_BY_NAME_QUERY),
//    @NamedQuery(name = User.FIND_BY_USERNAME, query = User.FIND_BY_USERNAME_QUERY)
//})
public class User {
    // Queries
    public static final String FIND_BY_NAME = "User.findByName";
    public static final String FIND_BY_NAME_QUERY = "SELECT u FROM Users u WHERE u.userName = ?1";
    public static final String FIND_BY_USERNAME = "User.findByUsername";
    public static final String FIND_BY_USERNAME_QUERY = "SELECT u FROM Users u WHERE u.userName =?1";

    @Id
    @Column(name = "USERID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userId;
    
    @Column(name = "USERNAME", nullable = false, updatable = false, length = 100)
    private String userName;
    
    public User() {
    }

    public User(String userName) {
        // this();
        this.userName = userName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
