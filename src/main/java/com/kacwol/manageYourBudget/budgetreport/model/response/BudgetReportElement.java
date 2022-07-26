package com.kacwol.manageYourBudget.budgetreport.model.response;

import com.kacwol.manageYourBudget.category.model.CategoryDto;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Objects;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class BudgetReportElement {

    private CategoryDto category;

    private double incomeSum;

    private double expenseSum;

}
