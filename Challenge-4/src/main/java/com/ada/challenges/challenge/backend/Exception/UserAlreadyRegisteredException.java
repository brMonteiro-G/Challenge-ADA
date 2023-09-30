package com.ada.challenges.challenge.backend.Exception;

public class UserAlreadyRegisteredException extends RuntimeException {

    public UserAlreadyRegisteredException(String errorMessage) {
        super(errorMessage);
    }


}
