package com.scaler.userservice.dto;

 import com.scaler.userservice.models.Token;
 import lombok.Data;

 import java.util.Date;

@Data
public class UserLoginResponseDto {
    private String token;
    private String message;
    private Date expiry;
    private boolean active;

    public static UserLoginResponseDto from(Token t) {

        UserLoginResponseDto responseDto = new UserLoginResponseDto();
        responseDto.setActive(true);
        responseDto.setExpiry(t.getExpiryDate());
        responseDto.setToken(t.getToken());
        responseDto.setMessage("User Successfully logged in");
        return responseDto;
    }
}