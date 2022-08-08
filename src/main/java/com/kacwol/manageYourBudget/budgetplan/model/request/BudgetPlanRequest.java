package com.kacwol.manageYourBudget.budgetplan.model.request;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class BudgetPlanRequest {

    int year;

    int month;

    List<BudgetPlanRequestElement> elements;
}
