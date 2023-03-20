package com.ddd.toy.pet.salon.domain;

import com.ddd.toy.pet.salon.application.OrderService;
import com.ddd.toy.pet.salon.application.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceMockTest {

    @Mock
    private UserService userService;
    @InjectMocks
    private OrderService orderService;


    private User testUser1;

    @BeforeEach
    void init() {
        testUser1 = new User(10L, "test1", "테스트1", "test1@test.com", "1234");
    }


    @Test
    @DisplayName("Mock Test")
    void findShortestPathNotConnectedException() {
        when(userService.findUserById(10L)).thenReturn(testUser1);

        User result = orderService.testMockInject(10L);

        assertAll(
                () -> assertThat(result.getUserNo()).isNotNull(),
                () -> assertThat(result.getPassword()).isEqualTo(testUser1.getPassword()),
                () -> assertThat(result.getName()).isEqualTo(testUser1.getName()),
                () -> assertThat(result.getEmail()).isEqualTo(testUser1.getEmail())
        );
    }

}
