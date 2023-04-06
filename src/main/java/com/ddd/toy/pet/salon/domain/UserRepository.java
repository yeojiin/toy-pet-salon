package com.ddd.toy.pet.salon.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository  extends JpaRepository<User, Long> {
    @Override
    List<User> findAll();

}
