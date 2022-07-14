package com.kacwol.manageYourBudget.budgetchange.service;

import com.kacwol.manageYourBudget.budgetchange.model.request.AllBudgetChangesByCategoryAndTimeDto;
import com.kacwol.manageYourBudget.budgetchange.model.BudgetChange;
import com.kacwol.manageYourBudget.budgetchange.model.request.BudgetChangeDto;
import com.kacwol.manageYourBudget.budgetchange.model.response.BudgetChangeResponseDto;

import java.time.LocalDate;
import java.util.List;

public interface BudgetChangeService {

    void addBudgetChange(BudgetChangeDto budgetChange);

    BudgetChangeResponseDto getBudgetChangeById(Long id);

    void deleteBudgetChangeById(Long id);

    List<BudgetChange> getAllBudgetChanges();

    List<BudgetChange> getAllByUserIdAndCategoryId(Long userId, Long categoryId);

    List<BudgetChange> getAllByUserIdAndCategoryIdBetweenDates(Long userId, AllBudgetChangesByCategoryAndTimeDto dto);
    List<BudgetChange> getAllByUserIdBetweenDates(Long userId, LocalDate start, LocalDate end);

}
