package com.scaler.userservice.exceptions;

public class PasswordNotMatchedException extends RuntimeException{
    public PasswordNotMatchedException(String message){
        super(message);
    }
}
