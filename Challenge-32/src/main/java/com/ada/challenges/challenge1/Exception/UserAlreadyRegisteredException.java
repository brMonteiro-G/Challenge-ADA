package com.ada.challenges.challenge1.Exception;

public class UserAlreadyRegisteredException extends RuntimeException {

    public UserAlreadyRegisteredException(String errorMessage) {
        super(errorMessage);
    }


}
