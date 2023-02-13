package com.kacwol.manageYourBudget.budgetchange.model;


import com.kacwol.manageYourBudget.category.model.Category;
import com.kacwol.manageYourBudget.user.model.User;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@DiscriminatorValue("INCOME")
public class Income extends BudgetChange {

    public Income(Long id, User user, Category category, LocalDate dateTime, BigDecimal value, String description) {
        super(id, user, category, dateTime, value, description);
    }
}
