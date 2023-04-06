package com.ddd.toy.pet.salon.dto;


import com.ddd.toy.pet.salon.domain.Pet;
import com.ddd.toy.pet.salon.domain.User;
import lombok.Data;

@Data
public class PetRequest {
    private String name;
    private String gender;
    private int age;
    private String type;
    private Long userNo;

    protected PetRequest() {
    }

    public Long getUserNo() {
        return userNo == null ? 0L : userNo;
    }

    public PetRequest(String name, String gender, int age, String type, Long userNo) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.type = type;
        this.userNo = userNo;
    }
    public PetRequest(String name, String gender, int age, String type) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.type = type;
    }

    public Pet toPet(User user) {
        if(user != null) {
            return new Pet(name, gender, age, type, user);
        }
        return new Pet(name, gender, age, type);
    }
}
