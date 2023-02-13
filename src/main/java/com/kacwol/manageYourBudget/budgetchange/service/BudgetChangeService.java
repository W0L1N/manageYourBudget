package com.kacwol.manageYourBudget.budgetchange.service;

import com.kacwol.manageYourBudget.budgetchange.model.BudgetChange;
import com.kacwol.manageYourBudget.budgetchange.model.Expense;
import com.kacwol.manageYourBudget.budgetchange.model.Income;
import com.kacwol.manageYourBudget.budgetchange.model.request.AllBudgetChangesByCategoryAndTimeDto;
import com.kacwol.manageYourBudget.budgetchange.model.request.BudgetChangeDto;
import com.kacwol.manageYourBudget.budgetchange.model.response.BudgetChangeResponseDto;
import org.springframework.security.core.Authentication;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public interface BudgetChangeService {

    void addBudgetChange(Authentication authentication, BudgetChangeDto budgetChange);

    BudgetChangeResponseDto getBudgetChangeById(Authentication authentication, Long id);

    void deleteBudgetChangeById(Authentication authentication, Long id);

    List<BudgetChange> getAllBudgetChanges(Authentication authentication);

    List<BudgetChange> getAllByUserIdAndCategoryId(Authentication authentication, Long categoryId);

    List<BudgetChange> getAllByUserIdAndCategoryIdBetweenDates(Authentication authentication, AllBudgetChangesByCategoryAndTimeDto dto);

    List<BudgetChange> getAllBudgetChanges(Authentication auth, LocalDate startDate, LocalDate endDate);

    LinkedList<Expense> getAllExpenses(Authentication auth, LocalDate startDate, LocalDate endDate);

    LinkedList<Income> getAllIncomes(Authentication auth, LocalDate startDate, LocalDate endDate);
}
