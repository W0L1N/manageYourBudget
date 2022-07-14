package com.kacwol.manageYourBudget.budgetchange.model.request;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BudgetChangeDto {

    private Long categoryId;

    private LocalDate dateTime;

    private double value;

    private String description;
}
