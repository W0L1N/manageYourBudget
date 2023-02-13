package com.kacwol.manageYourBudget.budgetchange.model.response;

import com.kacwol.manageYourBudget.category.model.CategoryDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class BudgetChangeResponseDto {

    private CategoryDto category;

    private LocalDate dateTime;

    private BigDecimal value;

    private String description;
}
