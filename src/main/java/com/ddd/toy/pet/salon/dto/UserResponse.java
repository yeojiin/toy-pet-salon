package com.ddd.toy.pet.salon.dto;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
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
