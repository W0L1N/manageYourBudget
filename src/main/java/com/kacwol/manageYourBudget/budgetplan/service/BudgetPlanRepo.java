package com.kacwol.manageYourBudget.budgetplan.service;

import com.kacwol.manageYourBudget.budgetplan.model.BudgetPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BudgetPlanRepo extends JpaRepository<BudgetPlan, Long> {
    Optional<BudgetPlan> findByIdAndUserId(Long id, Long userId);
}
