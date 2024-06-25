package com.scaler.userservice.dto;

import com.scaler.userservice.models.Roles;
import com.scaler.userservice.models.User;
import lombok.Data;

import java.util.List;
@Data
public class UserSignupResponseDto {

    private String name;
    private String email;
    private List<Roles> roles;


    public static UserSignupResponseDto from(User u){
        UserSignupResponseDto responseDto = new UserSignupResponseDto();
        responseDto.setEmail(u.getEmail());
        responseDto.setName(u.getName());
        responseDto.setRoles(u.getRoles());
        return responseDto;
    }
}
