package com.kacwol.manageYourBudget.budgetchange.model.request;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AllBudgetChangesByCategoryAndTimeDto {

    private Long categoryId;

    private LocalDate start;

    private LocalDate end;
}
