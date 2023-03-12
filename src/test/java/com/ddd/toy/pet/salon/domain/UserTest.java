package com.ddd.toy.pet.salon.domain;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class UserTest {
    public static final User yeojiin = new User(null, "yeojiin", "bbasshongMon", "yeojiin@gmail.com", "1234");
}
