package com.ada.challenges.challenge1.Exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String errorMessage) {
        super(errorMessage);
    }

}
