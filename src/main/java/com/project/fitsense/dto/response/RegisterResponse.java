package com.project.fitsense.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class RegisterResponse {
    private UUID userID;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private int weight;
    private String createdAt;
    private String updatedAt;

}
