package com.kacwol.manageYourBudget.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class ExceptionMessage {
    private String exception;
    private String message;
    private HttpStatus httpStatus;
    private LocalDateTime timestamp;
}
