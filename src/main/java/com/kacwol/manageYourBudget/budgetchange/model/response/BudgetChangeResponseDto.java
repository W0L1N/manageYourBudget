package com.kacwol.manageYourBudget.budgetchange.model.response;

import com.kacwol.manageYourBudget.category.model.CategoryDto;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BudgetChangeResponseDto {

    private CategoryDto category;

    private LocalDate dateTime;

    private double value;

    private String description;
}
