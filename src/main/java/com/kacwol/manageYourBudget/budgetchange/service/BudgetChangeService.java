package com.kacwol.manageYourBudget.budgetchange.service;

import com.kacwol.manageYourBudget.budgetchange.model.BudgetChange;
import com.kacwol.manageYourBudget.budgetchange.model.Expense;
import com.kacwol.manageYourBudget.budgetchange.model.Income;
import com.kacwol.manageYourBudget.budgetchange.model.request.AllBudgetChangesByCategoryAndTimeDto;
import com.kacwol.manageYourBudget.budgetchange.model.request.BudgetChangeDto;
import com.kacwol.manageYourBudget.budgetchange.model.response.BudgetChangeResponseDto;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public interface BudgetChangeService {

    void addBudgetChange(BudgetChangeDto budgetChange);

    BudgetChangeResponseDto getBudgetChangeById(Long id);

    void deleteBudgetChangeById(Long id);

    List<BudgetChange> getAllBudgetChanges();

    List<BudgetChange> getAllByUserIdAndCategoryId(Long categoryId);

    List<BudgetChange> getAllByUserIdAndCategoryIdBetweenDates(AllBudgetChangesByCategoryAndTimeDto dto);

    List<BudgetChange> getAllBudgetChanges(LocalDate startDate, LocalDate endDate);

    LinkedList<Expense> getAllExpenses(LocalDate startDate, LocalDate endDate);

    LinkedList<Income> getAllIncomes(LocalDate startDate, LocalDate endDate);
}
