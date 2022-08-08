package com.kacwol.manageYourBudget.budgetplan.model.response;

import com.kacwol.manageYourBudget.category.model.CategoryDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BudgetPlanElementResponseDto {

    private CategoryDto category;

    private double plannedValue;

    private double actualValue;

    private boolean wasMet;

}
