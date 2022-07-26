package com.kacwol.manageYourBudget.budgetreport.model.response;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class BudgetReportResponse {

    private LocalDate startDate;

    private LocalDate endDate;

    double expenseSum;

    double incomeSum;

    double sum;

    private List<BudgetReportElement> elementList;
}
