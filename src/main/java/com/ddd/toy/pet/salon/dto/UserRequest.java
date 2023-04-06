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

    public User toUser() {
        return new User(id, name, email, password);
    }
}
