package com.kacwol.manageYourBudget.exception;

public class BudgetPlanNotFoundException extends RuntimeException {

    public BudgetPlanNotFoundException() {
        super("Budget Plan not found.");
    }
}
