package com.kacwol.manageYourBudget.budgetreport.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class BudgetReportRequest {

    private LocalDate startDate;

    private LocalDate endDate;

}
