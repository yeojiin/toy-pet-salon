package com.ddd.toy.pet.salon.dto;


import com.ddd.toy.pet.salon.domain.User;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserResponse {
    private Long userNo;
    private String id;
    private String name;
    private String email;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    protected UserResponse() {
    }

    public UserResponse(User user) {
        this.userNo = user.getUserNo();
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.createdDate = user.getCreatedDate();
        this.modifiedDate = user.getModifiedDate();
    }

    public static UserResponse from(User user) {
        return new UserResponse(user);
    }

    public Long getUserNo() {
        return userNo;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }
}
