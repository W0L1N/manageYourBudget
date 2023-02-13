package com.kacwol.manageYourBudget.budgetchange.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;


@Getter
@AllArgsConstructor
public class BudgetChangeDto {

    private Long categoryId;

    private LocalDate dateTime;

    private BigDecimal value;

    private String description;
}
