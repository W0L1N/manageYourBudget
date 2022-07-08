package com.kacwol.manageYourBudget.budgetchange;

import com.kacwol.manageYourBudget.budgetchange.model.BudgetChange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetChangeRepo extends JpaRepository<BudgetChange, Long> {
}
