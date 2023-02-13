package com.kacwol.manageYourBudget.budgetchange;

import com.kacwol.manageYourBudget.budgetchange.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Repository
public interface ExpenseRepo extends JpaRepository<Expense, Long> {

    List<Expense> findAllByUserId(Long userId);

    List<Expense> findAllByUserIdAndCategoryId(Long userId, Long categoryId);

    List<Expense> findAllByUserIdAndCategoryIdAndDateTimeBetween(Long userId, Long categoryId, LocalDate dateTimeStart, LocalDate dateTimeEnd);

    LinkedList<Expense> findAllByUserIdAndDateTimeBetween(Long userId, LocalDate dateTimeStart, LocalDate dateTimeEnd);
}
