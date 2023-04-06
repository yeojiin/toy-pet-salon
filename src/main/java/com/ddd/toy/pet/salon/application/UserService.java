package com.ddd.toy.pet.salon.application;

import com.ddd.toy.pet.salon.domain.User;
import com.ddd.toy.pet.salon.domain.UserRepository;
import com.ddd.toy.pet.salon.dto.UserRequest;
import com.ddd.toy.pet.salon.dto.UserResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional(readOnly = true)
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserResponse saveUser(UserRequest userRequest) {
        User persistUser = userRepository.save(userRequest.toUser());
        return UserResponse.from(persistUser);
    }

    public User findUserById(Long id) {
        if(id == 0L) {
            return null;
        }
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException());
    }


    public List<UserResponse> findAllUsers() {
        List<User> users = userRepository.findAll();

        return users.stream()
                .map(UserResponse::from)
                .collect(Collectors.toList());
    }
}
