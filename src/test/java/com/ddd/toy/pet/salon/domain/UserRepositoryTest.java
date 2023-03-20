package com.ddd.toy.pet.salon.domain;

import com.ddd.toy.pet.salon.application.OrderService;
import com.ddd.toy.pet.salon.application.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.assertj.core.api.Assertions.assertThat;

import static com.ddd.toy.pet.salon.domain.UserTest.yeojiin;
import static org.mockito.Mockito.when;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    @Mock
    private UserService userService;
    @InjectMocks
    private OrderService orderService;


    private User testUser1;

    @BeforeEach
    void init() {
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("사용자를 저장할 수 있다")
//    @Rollback(false)
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
