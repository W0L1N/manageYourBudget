package com.kacwol.manageYourBudget.budgetchange.model;

import com.kacwol.manageYourBudget.category.model.CategoryDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class BudgetChangeResponseDto {

    private CategoryDto categoryId;

    private LocalDateTime timestamp;

    private double value;

    private String description;
}
