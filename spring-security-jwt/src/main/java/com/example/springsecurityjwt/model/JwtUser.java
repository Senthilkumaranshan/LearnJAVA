package com.example.springsecurityjwt.model;

public class JwtUser {


    private String userName;
    private long id;
    private String role;

    public void setUserName(String userName) {

        this.userName = userName;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
