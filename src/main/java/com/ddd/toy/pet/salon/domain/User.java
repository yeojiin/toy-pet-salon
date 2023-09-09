package com.ddd.toy.pet.salon.domain;

import com.ddd.toy.pet.salon.dto.UserRequest;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

@Entity
@Table(name = "user")
@Data
public class User extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_no", updatable = false, nullable = false)
    private Long userNo;

    private String id;
    private String name;
    private String email;
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Pet> pets = new ArrayList<Pet>();

//    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
//    @JoinColumn(name="target_No")
//    private List<Image> images = new ArrayList<>();

    protected User() {
    }

    public User(Long userNo, String id, String name, String email, String password) {
        this.userNo = userNo;
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User(String id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User(String id, String name, String email, String password, List<Pet> pets) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        if(!pets.isEmpty()) {
            addPets(pets);
        }
//        if(!images.isEmpty()) {
//            addImages(images);
//        }
    }

    private void addPets(List<Pet> pets) {
        pets.forEach(this::addPet);
    }

//    private void addImages(List<Image> images) {
//        images.forEach(this::addImage);
//    }

    private void addPet(Pet pet) {
//        if(!pet.getImages().isEmpty()) {
//            pet.addImages();
//        }

        this.pets.add(pet);
        pet.setUser(this);
    }

//    private void addImage(Image image) {
//        this.images.add(image);
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userNo, user.userNo) && Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(email, user.email) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userNo, id, name, email, password);
    }


    public void createUserId() {
        String prefix = "103";
        String newUserId = String.format("%03d", this.userNo);

        this.id = prefix.concat(newUserId);
    }

    public void deletePet() {
        this.pets = new ArrayList<>();
    }

    public void addNewPet(List<Pet> pets) {
        this.pets.addAll(pets);

        this.pets.forEach(p -> {
            p.changeUser(this);
        });
    }

    public void changeInfo(UserRequest userRequest) {
        this.name = userRequest.getName();
    }
}
