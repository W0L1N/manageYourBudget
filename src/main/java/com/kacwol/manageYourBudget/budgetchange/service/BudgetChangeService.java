package com.kacwol.manageYourBudget.budgetchange.service;

import com.kacwol.manageYourBudget.budgetchange.model.request.AllBudgetChangesByCategoryAndTimeDto;
import com.kacwol.manageYourBudget.budgetchange.model.BudgetChange;
import com.kacwol.manageYourBudget.budgetchange.model.request.BudgetChangeDto;
import com.kacwol.manageYourBudget.budgetchange.model.response.BudgetChangeResponseDto;
import org.springframework.security.core.Authentication;

import java.time.LocalDate;
import java.util.List;

public interface BudgetChangeService {

    void addBudgetChange(Authentication authentication, BudgetChangeDto budgetChange);

    BudgetChangeResponseDto getBudgetChangeById(Authentication authentication, Long id);

    void deleteBudgetChangeById(Authentication authentication, Long id);

    List<BudgetChange> getAllBudgetChanges(Authentication authentication);

    List<BudgetChange> getAllByUserIdAndCategoryId(Authentication authentication, Long categoryId);

    List<BudgetChange> getAllByUserIdAndCategoryIdBetweenDates(Authentication authentication, AllBudgetChangesByCategoryAndTimeDto dto);

    List<BudgetChange> getAllByUserIdBetweenDates(Authentication authentication, LocalDate start, LocalDate end);

    List<BudgetChange> getAllBudgetChanges(Authentication auth, LocalDate startDate, LocalDate endDate);
}
