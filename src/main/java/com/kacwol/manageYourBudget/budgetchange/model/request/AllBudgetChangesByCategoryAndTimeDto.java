package com.kacwol.manageYourBudget.budgetchange.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;


@Getter
@AllArgsConstructor
public class AllBudgetChangesByCategoryAndTimeDto {

    private Long categoryId;

    private LocalDate start;

    private LocalDate end;
}
