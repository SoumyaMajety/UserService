package com.scaler.userservice.controller;

import com.scaler.userservice.dto.*;
import com.scaler.userservice.models.Token;
import com.scaler.userservice.models.User;
import com.scaler.userservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {

        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserSignupResponseDto> signup(@RequestBody UserSignupRequestDto requestDto) throws Exception {
        User u = userService.signUp(requestDto.getName(), requestDto.getEmail(), requestDto.getPassword());
        return new ResponseEntity<>(UserSignupResponseDto.from(u), HttpStatus.OK);

    }

    @PostMapping("/login")
    public UserLoginResponseDto login(@RequestBody UserLoginRequestDto requestDto) {
        Token t = userService.login(requestDto.getEmail(), requestDto.getPassword());
        return UserLoginResponseDto.from(t);
    }

//        UserLoginResponseDto responseDto = new UserLoginResponseDto();
//        responseDto.setActive(true);
//        responseDto.setExpiry(t.getExpiryDate());
//        responseDto.setToken(t.getToken());
//        responseDto.setMessage("User Successfully logged in");
//        return responseDto;


    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestBody LogoutDto logoutDto) {
      User u =  userService.logout(logoutDto.getToken());
        return new ResponseEntity<>(u.getName()+ "  logged out", HttpStatus.OK);
    }

    @GetMapping("validate/{token}")
    public UserSignupResponseDto validateToken(@PathVariable("token") String token) {

        User u = userService.validateToken(token);
        return UserSignupResponseDto.from(u);
    }

    @GetMapping("/users/{id}")
    public UserSignupResponseDto getUserById(@PathVariable Long id) {
        return null;
    }

}
