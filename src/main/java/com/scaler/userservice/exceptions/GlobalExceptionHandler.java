package com.scaler.userservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<String> userAlreadyExistsException(UserAlreadyExistsException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> userNotFoundException(UserNotFoundException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TokenNotFoundException.class)
    public ResponseEntity<String> tokenNotFoundException(TokenNotFoundException e){
        return new ResponseEntity<>( e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PasswordNotMatchedException.class)
    public ResponseEntity<String> passwordNotMatchedException(PasswordNotMatchedException e){
        return new ResponseEntity<>( e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(NoOfLoginsExceededException.class)
    public ResponseEntity<String> NoOfLoginsExceededException(NoOfLoginsExceededException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
