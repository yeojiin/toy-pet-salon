package com.ddd.toy.pet.salon.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    }

    private void addPets(List<Pet> pets) {
        pets.forEach(this::addPet);
    }

    private void addPet(Pet pet) {
        this.pets.add(pet);
        pet.setUser(this);
    }

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
}
