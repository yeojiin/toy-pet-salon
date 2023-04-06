package com.ddd.toy.pet.salon.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "pet")
@Data
public class Pet extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "petNo", updatable = false, nullable = false)
    private Long petNo;
    private String name;
    private String gender;
    private int age;
    private String type;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_no")   //FK가 있는 곳이 연관관계의 주인
    private User user;

    protected Pet() {
    }

    public Pet(String name, String gender, int age, String type) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.type = type;
    }
    public Pet(String name, String gender, int age, String type, User user) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.type = type;
        this.user = user;
    }

    public Long getUserNo() {
        return user != null ? user.getUserNo() : null;
    }
    public String getUserName() {
        return user != null ? user.getName() : null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pet)) return false;
        Pet pet = (Pet) o;
        return getAge() == pet.getAge() && Objects.equals(getPetNo(), pet.getPetNo()) && Objects.equals(getName(), pet.getName()) && Objects.equals(getGender(), pet.getGender()) && Objects.equals(getType(), pet.getType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPetNo(), getName(), getGender(), getAge(), getType());
    }



}
