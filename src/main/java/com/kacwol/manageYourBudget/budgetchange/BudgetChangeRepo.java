package com.kacwol.manageYourBudget.budgetchange;

import com.kacwol.manageYourBudget.budgetchange.model.BudgetChange;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetChangeRepo extends JpaRepository<BudgetChange, Long> {
    List<BudgetChange> findAllByUserIdAndCategoryId(Long userId, Long categoryId);

    List<BudgetChange> findAllByUserIdAndCategoryIdAndDateTimeBetween(Long userId, Long categoryId, LocalDate dateTimeStart ,LocalDate dateTimeEnd);

    List<BudgetChange>findAllByUserIdAndDateTimeBetween(Long userId, LocalDate dateTimeStart, LocalDate dateTimeEnd);

}
