package com.kacwol.manageYourBudget.budgetplan.model.request;

import com.kacwol.manageYourBudget.category.model.CategoryDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BudgetPlanRequestElement {

    private CategoryDto category;

    private double value;
}
