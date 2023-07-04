package com.kacwol.manageYourBudget.budgetplan.service;

import com.kacwol.manageYourBudget.budgetplan.model.BudgetPlanElement;
import com.kacwol.manageYourBudget.budgetplan.model.request.BudgetPlanRequestElement;
import com.kacwol.manageYourBudget.budgetplan.model.response.BudgetPlanElementResponseDto;
import com.kacwol.manageYourBudget.budgetreport.model.response.BudgetReportElement;
import com.kacwol.manageYourBudget.category.model.Category;
import com.kacwol.manageYourBudget.category.service.CategoryServiceImpl;
import com.kacwol.manageYourBudget.user.service.AuthService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class BudgetPlanMapper {

    private final AuthService userService;

    private final CategoryServiceImpl categoryService;


    public BudgetPlanElementResponseDto planElementToDto(BudgetReportElement reportElement, BudgetPlanElement planElement) {

        BigDecimal plannedValue = planElement.getValue();
        BigDecimal actualValue = reportElement.getExpenseSum();

        return new BudgetPlanElementResponseDto(
                reportElement.getCategory(),
                planElement.getValue(),
                reportElement.getExpenseSum(),
                plannedValue.doubleValue() > actualValue.doubleValue()
        );
    }

    public BudgetPlanElement elementToEntity(BudgetPlanRequestElement element) {
        Category category = categoryService.getCategoryById(element.getCategory().getId());
        return new BudgetPlanElement(null, category, element.getValue());
    }


}
