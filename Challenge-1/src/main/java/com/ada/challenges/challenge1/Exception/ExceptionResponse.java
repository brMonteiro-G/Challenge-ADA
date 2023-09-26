package com.ada.challenges.challenge1.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ExceptionResponse {

    private HttpStatus status;
    private List<String> errors;
    private String field;

}
