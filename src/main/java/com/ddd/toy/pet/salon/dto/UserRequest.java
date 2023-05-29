package com.ddd.toy.pet.salon.dto;


import com.ddd.toy.pet.salon.domain.Pet;
import com.ddd.toy.pet.salon.domain.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserRequest {
    private String id;
    private String name;
    private String email;
    private String password;
    private List<Pet> pets = new ArrayList<>();

    protected UserRequest() {
    }

    public UserRequest(String id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User toUser() {
        return new User(id, name, email, password, pets);
    }
}
