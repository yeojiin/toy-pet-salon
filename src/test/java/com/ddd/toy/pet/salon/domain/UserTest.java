package com.ddd.toy.pet.salon.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class UserTest {
    public static final User yeojiin = new User(1L, "yeojiin", "bbasshongMon", "yeojiin@gmail.com", "1234");
}
