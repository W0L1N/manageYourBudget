package com.kacwol.manageYourBudget.budgetplan.model.response;

import com.kacwol.manageYourBudget.category.model.CategoryDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class BudgetPlanElementResponseDto {

    private CategoryDto category;

    private BigDecimal plannedValue;

    private BigDecimal actualValue;

    private boolean wasMet;

}
