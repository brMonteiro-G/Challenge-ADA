package com.ada.challenges.challenge.backend.Exception;

public class QueueEmptyException extends RuntimeException {


    public QueueEmptyException(String errorMessage) {
        super(errorMessage);
    }

}
