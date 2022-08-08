package com.kacwol.manageYourBudget.budgetplan.service;

import com.kacwol.manageYourBudget.budgetplan.model.BudgetPlanElement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetPlanElementRepo extends JpaRepository<BudgetPlanElement, Long> {
}
