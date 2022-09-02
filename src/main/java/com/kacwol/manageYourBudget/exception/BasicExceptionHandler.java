package com.kacwol.manageYourBudget.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;


@ControllerAdvice
public class BasicExceptionHandler {

    @ExceptionHandler(BudgetPlanNotFoundException.class)
    public ResponseEntity<ExceptionMessage> handleBudgetPlanNotFoundException(BudgetPlanNotFoundException e) {
        return basicHandleException(e, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BudgetChangeNotFoundException.class)
    public ResponseEntity<BudgetChangeNotFoundException> handleBudgetChangeNotFoundException(BudgetChangeNotFoundException e) {
        return basicHandleException(e, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<CategoryNotFoundException> handleCategoryNotFoundException(CategoryNotFoundException e) {
        return basicHandleException(e, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNameAlreadyExistsException.class)
    public ResponseEntity<UserNameAlreadyExistsException> handleUserNameAlreadyExistsException(UserNameAlreadyExistsException e) {
        return basicHandleException(e, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadDatesException.class)
    public ResponseEntity<BadDatesException> handleBadDatesException(BadDatesException e) {
        return basicHandleException(e, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadValueException.class)
    public ResponseEntity<BadValueException> handleBadValueException(BadValueException e) {
        return basicHandleException(e, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity basicHandleException(Exception e, HttpStatus httpStatus) {

        String[] longName = e.getClass().getTypeName().split("\\.");
        String shortName = longName[longName.length - 1];

        return new ResponseEntity<>(
                new ExceptionMessage(
                        shortName,
                        e.getMessage(),
                        httpStatus,
                        LocalDateTime.now()
                ),
                httpStatus
        );


    }

}
