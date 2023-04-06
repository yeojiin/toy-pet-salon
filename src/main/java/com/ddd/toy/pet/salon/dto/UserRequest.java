package com.ddd.toy.pet.salon.dto;


import com.ddd.toy.pet.salon.domain.User;
import lombok.Data;

@Data
public class UserRequest {
    private String id;
    private String name;
    private String email;
    private String password;

    protected UserRequest() {
    }

    public UserRequest(String id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User toUser() {
        return new User(id, name, email, password);
    }
}
