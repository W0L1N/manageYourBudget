package com.kacwol.manageYourBudget.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class BudgetChangeNotFoundException extends RuntimeException {
    public BudgetChangeNotFoundException() {
        super("Budget change not found.");
    }
}
