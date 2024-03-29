package com.kacwol.manageYourBudget.budgetplan.model.request;

import com.kacwol.manageYourBudget.category.model.CategoryDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;


@Getter
@AllArgsConstructor
public class BudgetPlanRequestElement {

    private CategoryDto category;

    private BigDecimal value;
}
