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


    protected PetRequest() {
    }

    public Pet toPet() {
        return new Pet(name, gender, age, type);
    }
}
