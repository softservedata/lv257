package edu.softserve.model;

public class UserInfo {

    private String userName;
    private String password;

    public UserInfo()  {

    }

    // Do not change this constructor, it used in hibernate Query.
    public UserInfo(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}