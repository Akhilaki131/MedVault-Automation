package com.hospital.test.model;

public class UserDetails {

    private String username;

    private String password;

    private String email;

    public String getuserName() {
        return username;
    }

    public void setUserName(String userName) {
        this.username = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserDetails(String username, String password, String email) {
        super();
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public UserDetails() {
        // TODO Auto-generated constructor stub
    }

}
