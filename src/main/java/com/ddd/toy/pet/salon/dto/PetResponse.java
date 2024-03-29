package com.ddd.toy.pet.salon.dto;


import com.ddd.toy.pet.salon.domain.Pet;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PetResponse {
    private Long petNo;
    private String name;
    private String gender;
    private int age;
    private String type;
    private String petId;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    private Long userNo;
    private String userName;

    public PetResponse(Pet pet) {
        this.petNo = pet.getPetNo();
        this.name = pet.getName();
        this.gender = pet.getGender();
        this.age = pet.getAge();
        this.type = pet.getType();
        this.createdDate = pet.getCreatedDate();
        this.modifiedDate = pet.getModifiedDate();
        this.userNo = pet.getUserNo();
        this.userName = pet.getUserName();
        this.petId = pet.getPetId();
    }

    public static PetResponse from(Pet pet) {
        return new PetResponse(pet);
    }

}
