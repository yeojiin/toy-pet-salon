package com.ddd.toy.pet.salon.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.*;

public interface UserRepository  extends JpaRepository<User, Long> {

}
