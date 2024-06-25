package com.scaler.userservice.dto;

import lombok.Data;

@Data
public class UserSignupRequestDto {
    private String name;
    private String email;
    private String password;

}
