package com.ddd.toy.pet.salon.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.assertj.core.api.Assertions.assertThat;

import static com.ddd.toy.pet.salon.domain.UserTest.yeojiin;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void init() {
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("사용자를 저장할 수 있다")
    void save_user() {
        // when
        User user = userRepository.save(yeojiin);

        assertAll(
                () -> assertThat(user.getUserNo()).isNotNull(),
                () -> assertThat(user.getPassword()).isEqualTo(yeojiin.getPassword()),
                () -> assertThat(user.getName()).isEqualTo(yeojiin.getName()),
                () -> assertThat(user.getEmail()).isEqualTo(yeojiin.getEmail())
        );
    }

}
