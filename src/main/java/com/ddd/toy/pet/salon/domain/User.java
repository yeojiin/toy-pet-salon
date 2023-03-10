package com.ddd.toy.pet.salon.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userNo", updatable = false, nullable = false)
    private Long userNo;

    private String id;
    private String name;
    private String email;
    private String password;

}
