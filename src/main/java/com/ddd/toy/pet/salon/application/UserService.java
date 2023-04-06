package com.ddd.toy.pet.salon.application;

import com.ddd.toy.pet.salon.domain.User;
import com.ddd.toy.pet.salon.domain.UserRepository;
import com.ddd.toy.pet.salon.dto.UserRequest;
import com.ddd.toy.pet.salon.dto.UserResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserResponse saveUser(UserRequest userRequest) {
        User user = User.builder()
                .id(userRequest.getId())
                .email(userRequest.getEmail())
                .name(userRequest.getName())
                .password(userRequest.getPassword())
                .build();
        User persistUser = userRepository.save(user);

        return UserResponse.builder()
                .userNo(persistUser.getUserNo())
                .name(persistUser.getName())
                .email(persistUser.getEmail())
                .createdDate(persistUser.getCreatedDate())
                .build();
    }

    public User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException());
    }


}
