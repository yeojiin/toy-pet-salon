package com.ddd.toy.pet.salon.dto;


public class UserRequest {
    private String id;
    private String name;
    private String email;
    private String password;


    protected UserRequest() {
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
