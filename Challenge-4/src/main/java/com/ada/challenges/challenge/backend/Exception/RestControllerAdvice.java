package com.ada.challenges.challenge.backend.Exception;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@org.springframework.web.bind.annotation.RestControllerAdvice

public class RestControllerAdvice {

    private final MessageSource messageSource;

    public RestControllerAdvice(MessageSource messageSource) {
        this.messageSource = messageSource;
    }


    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ExceptionResponse> handle(MethodArgumentNotValidException exception) {
        List<ExceptionResponse> listOfException = new ArrayList<>();

        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        fieldErrors.forEach(e -> {
            String message = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            ExceptionResponse error = new ExceptionResponse(HttpStatus.BAD_REQUEST, Collections.singletonList(message), e.getField());
            listOfException.add(error);
        });

        return listOfException;
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public ExceptionResponse handle(UserNotFoundException exception) {

        ExceptionResponse exceptionResponse = new ExceptionResponse();

        exceptionResponse.setStatus(HttpStatus.BAD_REQUEST);
        exceptionResponse.setErrors(Collections.singletonList(exception.getMessage()));

        return exceptionResponse;
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserAlreadyRegisteredException.class)
    public ExceptionResponse handle(UserAlreadyRegisteredException exception) {

        ExceptionResponse exceptionResponse = new ExceptionResponse();

        exceptionResponse.setStatus(HttpStatus.BAD_REQUEST);
        exceptionResponse.setErrors(Collections.singletonList(exception.getMessage()));

        return exceptionResponse;
    }

    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(QueueEmptyException.class)
    public ExceptionResponse handle(QueueEmptyException exception) {

        ExceptionResponse exceptionResponse = new ExceptionResponse();

        exceptionResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        exceptionResponse.setErrors(Collections.singletonList(exception.getMessage()));

        return exceptionResponse;
    }

    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(QueueFullException.class)
    public ExceptionResponse handle(QueueFullException exception) {

        ExceptionResponse exceptionResponse = new ExceptionResponse();

        exceptionResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        exceptionResponse.setErrors(Collections.singletonList(exception.getMessage()));

        return exceptionResponse;
    }


}
