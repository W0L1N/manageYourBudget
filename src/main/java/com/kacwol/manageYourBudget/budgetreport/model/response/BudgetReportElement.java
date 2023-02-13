package com.kacwol.manageYourBudget.budgetreport.model.response;

import com.kacwol.manageYourBudget.category.model.CategoryDto;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;


@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class BudgetReportElement {

    private CategoryDto category;

    private BigDecimal incomeSum;

    private BigDecimal expenseSum;

}
