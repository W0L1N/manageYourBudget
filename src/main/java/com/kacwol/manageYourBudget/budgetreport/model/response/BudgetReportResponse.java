package com.kacwol.manageYourBudget.budgetreport.model.response;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class BudgetReportResponse {

    private LocalDate startDate;

    private LocalDate endDate;

    BigDecimal expenseSum;

    BigDecimal incomeSum;

    BigDecimal sum;

    private List<BudgetReportElement> elementList;
}
