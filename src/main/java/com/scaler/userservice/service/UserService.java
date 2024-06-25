package com.scaler.userservice.service;

import com.scaler.userservice.exceptions.*;
import com.scaler.userservice.models.Token;
import com.scaler.userservice.models.User;
import com.scaler.userservice.repo.TokenRepository;
import com.scaler.userservice.repo.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserRepository userRepository;
    private TokenRepository tokenRepository;

    public UserService(BCryptPasswordEncoder bCryptPasswordEncoder,
                       UserRepository userRepository,
                       TokenRepository tokenRepository){
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
        this.userRepository=userRepository;
        this.tokenRepository=tokenRepository;

    }
    public User signUp(String name,String email,String password) throws Exception {
            Optional<User> optionalUser = userRepository.findByEmail(email);
            if(!optionalUser.isEmpty()){
                throw new UserAlreadyExistsException("User already exists with given email");
            }
            User u = new User();
            u.setEmail(email);
            u.setName(name);
            u.setPassword(bCryptPasswordEncoder.encode(password));
            u.setVerified(false);
            User savedUser = userRepository.save(u);
            return savedUser;
    }

    public Token login(String email,String password){
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if(optionalUser.isEmpty()){
        throw new UserNotFoundException("User not found");
        }
            User u = optionalUser.get();
        if(!bCryptPasswordEncoder.matches(password,u.getPassword())){
            throw new PasswordNotMatchedException("Password does not matched");
        }

        Token t = generateToken(u);
        t  = tokenRepository.save(t);
        return t;
    }

    public Token generateToken(User u){
        // validating if there are 2 or motr logins
        List<Token> noOfActiveTokens =
                tokenRepository.findByUserAndIsValidAndExpiryDateGreaterThan(u,true,new Date());
        if(noOfActiveTokens.size() >=2){
            throw new NoOfLoginsExceededException("User login limit exceeded");
        }
        Token t = new Token();
        LocalDate currentDate = LocalDate.now();
        LocalDate expiryDate = currentDate.plusDays(30);
        Date TokenExpiryDate = Date.from(expiryDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        t.setExpiryDate(TokenExpiryDate);
        t.setUser(u);
        t.setValid(true);
        t.setToken(RandomStringUtils.randomAlphanumeric(128));

        return t;
    }

    public User logout(String token) {

        Optional<Token> optionalToken = tokenRepository.findByTokenAndIsValid(token,true);
        if(optionalToken.isEmpty()){
            throw new TokenNotFoundException("Token is not valid");
        }
            Token t = optionalToken.get();
        t.setValid(false);
        tokenRepository.save(t);
        return t.getUser();
    }

    public User validateToken(String token) {
        Optional<Token> optionalToken = tokenRepository
                .findByTokenAndIsValidAndExpiryDateGreaterThan(token,true,new Date());
        if(optionalToken.isEmpty()){
            throw new TokenNotFoundException("Invalid Token");
        }
        return optionalToken.get().getUser();
    }
}
