package com.kacwol.manageYourBudget.ignore;

import com.kacwol.manageYourBudget.budgetplan.model.request.BudgetPlanRequest;
import com.kacwol.manageYourBudget.budgetplan.model.response.BudgetPlanResponseDto;
import com.kacwol.manageYourBudget.budgetplan.service.BudgetPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/budgetPlan")
public class BudgetPlanController {

    private final BudgetPlanService budgetPlanService;

    @Autowired
    public BudgetPlanController(BudgetPlanService budgetPlanService) {
        this.budgetPlanService = budgetPlanService;
    }

    @PostMapping
    public void addBudgetPlan(Authentication auth, @RequestBody BudgetPlanRequest request) {
        budgetPlanService.addBudgetPlan(auth, request);
    }

    @GetMapping("/report/{id}")
    public BudgetPlanResponseDto getBudgetPlanReport(Authentication authentication, @PathVariable Long id) {
        return budgetPlanService.getBudgetPlanReport(authentication, id);
    }
}
