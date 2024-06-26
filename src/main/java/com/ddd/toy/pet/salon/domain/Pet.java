package com.ddd.toy.pet.salon.domain;

import antlr.StringUtils;
import lombok.Data;

import javax.persistence.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "pet")
@Data
public class Pet extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pet_no", updatable = false, nullable = false)
    private Long petNo;
    private String name;
    private String gender;
    private int age;
    private String type;

    @Column(name = "pet_id", updatable = false, nullable = false)
    private String petId;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_no")   //FK가 있는 곳이 연관관계의 주인        name:여기서 연결할 컬럼, refre~: 부모테이블 연결 컬럼
    private User user;

//    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "pet")
//    private List<Image> images = new ArrayList<>();

    public Pet() {
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


    public void createPetId() {
        String prefix = "P";
        String newPetId = String.format("%03d", this.petNo);

        this.petId = prefix.concat(newPetId);
    }

    public void addImages() {
//        if(!images.isEmpty()) {
//            images.forEach(this::addImage);
//        }

    }

    private void addImage(Image image) {
//        images.add(image);
    }

    public void changeUser(User user) {
        this.user = user;
    }
}
