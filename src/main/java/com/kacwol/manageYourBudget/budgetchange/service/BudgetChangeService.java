package com.kacwol.manageYourBudget.budgetchange.service;

import com.kacwol.manageYourBudget.budgetchange.model.BudgetChange;
import com.kacwol.manageYourBudget.budgetchange.model.BudgetChangeDto;
import com.kacwol.manageYourBudget.budgetchange.model.BudgetChangeResponseDto;
import java.util.List;

public interface BudgetChangeService {

    void addBudgetChange(BudgetChangeDto budgetChange);

    BudgetChangeResponseDto getBudgetChangeById(Long id);

    void deleteBudgetChangeById(Long id);

    List<BudgetChange> getAllBudgetChanges();

}
