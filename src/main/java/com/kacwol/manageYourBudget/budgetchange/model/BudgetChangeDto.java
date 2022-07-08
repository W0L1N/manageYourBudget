package com.kacwol.manageYourBudget.budgetchange.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BudgetChangeDto {

    private Long categoryId;

    private LocalDateTime timestamp;

    private double value;

    private String description;
}
