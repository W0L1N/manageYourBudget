package com.kacwol.manageYourBudget.budgetchange.model;

import com.kacwol.manageYourBudget.category.model.Category;
import com.kacwol.manageYourBudget.user.model.User;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@DiscriminatorValue("EXPENSE")
public class Expense extends BudgetChange{

    public Expense(Long id, User user, Category category, LocalDate dateTime, BigDecimal value, String description) {
        super(id, user, category, dateTime, value, description);
    }
}
