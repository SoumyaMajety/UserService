package com.scaler.userservice.exceptions;

public class NoOfLoginsExceededException extends RuntimeException{
    public NoOfLoginsExceededException(String message){
        super(message);
    }
}
