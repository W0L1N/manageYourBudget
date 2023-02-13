package com.kacwol.manageYourBudget.budgetchange;

import com.kacwol.manageYourBudget.budgetchange.model.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Repository
public interface IncomeRepo extends JpaRepository<Income, Long> {

    List<Income> findAllByUserId(Long userId);

    List<Income> findAllByUserIdAndCategoryId(Long userId, Long categoryId);

    List<Income> findAllByUserIdAndCategoryIdAndDateTimeBetween(Long userId, Long categoryId, LocalDate dateTimeStart, LocalDate dateTimeEnd);

    LinkedList<Income> findAllByUserIdAndDateTimeBetween(Long userId, LocalDate dateTimeStart, LocalDate dateTimeEnd);
}
