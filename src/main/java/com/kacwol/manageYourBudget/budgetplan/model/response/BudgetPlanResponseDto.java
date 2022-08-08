package com.kacwol.manageYourBudget.budgetplan.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class BudgetPlanResponseDto {

    int year;

    int month;

    List<BudgetPlanElementResponseDto> elements;
}
