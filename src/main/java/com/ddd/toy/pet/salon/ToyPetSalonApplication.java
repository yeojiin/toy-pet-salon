package com.ddd.toy.pet.salon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@EnableJpaAuditing
@SpringBootApplication
public class ToyPetSalonApplication {
    public static void main(String[] args) {
        SpringApplication.run(ToyPetSalonApplication.class, args);
    }

}
