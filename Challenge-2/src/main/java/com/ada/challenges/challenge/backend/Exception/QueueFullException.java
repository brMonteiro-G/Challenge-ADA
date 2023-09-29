package com.ada.challenges.challenge.backend.Exception;

public class QueueFullException extends RuntimeException {


    public QueueFullException(String errorMessage) {
        super(errorMessage);
    }

}
