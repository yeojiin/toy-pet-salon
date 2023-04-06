package com.ddd.toy.pet.salon.ui;

import com.ddd.toy.pet.salon.application.UserService;
import com.ddd.toy.pet.salon.dto.UserRequest;
import com.ddd.toy.pet.salon.dto.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
public class UserController {
    private UserService userService;

    public UserController(UserService userService) { this.userService = userService; }

    @PostMapping("/users")
    public ResponseEntity<UserResponse> registerUser(@RequestBody UserRequest userRequest) {
        UserResponse user = userService.saveUser(userRequest);
        return ResponseEntity.created(URI.create("/user/" + user.getId())).body(user);
    }

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserResponse>> showUsers() {
        return ResponseEntity.ok().body(userService.findAllUsers());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity handleIllegalArgsException() {
        return ResponseEntity.badRequest().build();
    }
}
