package com.kacwol.manageYourBudget.budgetplan.service;

import com.kacwol.manageYourBudget.budgetplan.model.BudgetPlanElement;
import com.kacwol.manageYourBudget.budgetplan.model.request.BudgetPlanRequestElement;
import com.kacwol.manageYourBudget.budgetplan.model.response.BudgetPlanElementResponseDto;
import com.kacwol.manageYourBudget.budgetreport.model.response.BudgetReportElement;
import com.kacwol.manageYourBudget.category.model.Category;
import com.kacwol.manageYourBudget.category.service.CategoryServiceImpl;
import com.kacwol.manageYourBudget.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class BudgetPlanMapper {

    private UserService userService;

    private CategoryServiceImpl categoryService;

    @Autowired
    public BudgetPlanMapper(UserService userService, CategoryServiceImpl categoryService) {
        this.userService = userService;
        this.categoryService = categoryService;
    }

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

    public BudgetPlanElement elementToEntity(Authentication auth, BudgetPlanRequestElement element) {
        Category category = categoryService.getCategoryById(auth, element.getCategory().getId());
        return new BudgetPlanElement(null, category, element.getValue());
    }


}
