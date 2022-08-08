package com.kacwol.manageYourBudget.budgetplan.service;

import com.kacwol.manageYourBudget.budgetplan.model.BudgetPlan;
import com.kacwol.manageYourBudget.budgetplan.model.BudgetPlanElement;
import com.kacwol.manageYourBudget.budgetplan.model.request.BudgetPlanRequest;
import com.kacwol.manageYourBudget.budgetplan.model.request.BudgetPlanRequestElement;
import com.kacwol.manageYourBudget.budgetplan.model.response.BudgetPlanElementResponseDto;
import com.kacwol.manageYourBudget.budgetreport.model.response.BudgetReportElement;
import com.kacwol.manageYourBudget.category.model.Category;
import com.kacwol.manageYourBudget.category.service.CategoryMapper;
import com.kacwol.manageYourBudget.category.service.CategoryService;
import com.kacwol.manageYourBudget.category.service.CategoryServiceImpl;
import com.kacwol.manageYourBudget.user.model.User;
import com.kacwol.manageYourBudget.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BudgetPlanMapper {


    //private final CategoryMapper categoryMapper;



    private UserService userService;

    private CategoryServiceImpl categoryService;

    @Autowired
    public BudgetPlanMapper(UserService userService, CategoryServiceImpl categoryService) {
        this.userService = userService;
        this.categoryService = categoryService;
    }

    public BudgetPlanElementResponseDto planElementToDto(BudgetReportElement reportElement, BudgetPlanElement planElement) {

        double plannedValue = planElement.getValue();
        double actualValue = reportElement.getExpenseSum();

        return new BudgetPlanElementResponseDto(
                reportElement.getCategory(),
                planElement.getValue(),
                reportElement.getExpenseSum(),
                plannedValue > actualValue
        );
    }

//    public BudgetPlan requestToEntity(Authentication auth, BudgetPlanRequest request) {
//
//
//        //return new BudgetPlan(null, user, elements, request.getYear(), request.getMonth());
//    }

    public BudgetPlanElement elementToEntity(Authentication auth, BudgetPlanRequestElement element) {
        Category category = categoryService.getCategoryById(auth, element.getCategory().getId());
        return new BudgetPlanElement(null, category, element.getValue());
    }


}
