package com.ddd.toy.pet.salon.ui;

import com.ddd.toy.pet.salon.application.UserService;
import com.ddd.toy.pet.salon.dto.UserRequest;
import com.ddd.toy.pet.salon.dto.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@Slf4j
@RestController
public class UserController {
    private UserService userService;

    public UserController(UserService userService) { this.userService = userService; }

    @PostMapping("/user")
    public ResponseEntity<UserResponse> registerUser(@RequestBody UserRequest userRequest) {
        UserResponse user = userService.saveUser(userRequest);
        StationResponse station = stationService.saveStation(stationRequest);
        return ResponseEntity.created(URI.create("/stations/" + station.getId())).body(station);
    }
}
