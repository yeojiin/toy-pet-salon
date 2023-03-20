package com.ddd.toy.pet.salon.application;

import com.ddd.toy.pet.salon.domain.User;
import com.ddd.toy.pet.salon.domain.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private UserService userService;

    public OrderService(UserService userService) {
        this.userService = userService;
    }

    public User testMockInject(Long id) {
        return findUserById(id);
    }

    private User findUserById(Long id) {
        return userService.findUserById(id);
    }
}
