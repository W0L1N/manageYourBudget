package com.kacwol.manageYourBudget.budgetchange.service;

import com.kacwol.manageYourBudget.budgetchange.model.BudgetChange;
import com.kacwol.manageYourBudget.budgetchange.model.Expense;
import com.kacwol.manageYourBudget.budgetchange.model.Income;
import com.kacwol.manageYourBudget.budgetchange.model.request.BudgetChangeDto;
import com.kacwol.manageYourBudget.budgetchange.model.response.BudgetChangeResponseDto;
import com.kacwol.manageYourBudget.category.model.Category;
import com.kacwol.manageYourBudget.category.service.CategoryMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class BudgetChangeMapper {

    private final CategoryMapper categoryMapper;

    public BudgetChange dtoToEntity(BudgetChangeDto dto, Category category) {
        LocalDate actual = dto.getDateTime() == null ? LocalDate.now() : dto.getDateTime();
        BudgetChange budgetChange = BudgetChange.builder()
                .category(category)
                .value(dto.getValue())
                .description(dto.getDescription())
                .dateTime(actual)
                .user(category.getUser())
                .build();
        return (dto.getValue().doubleValue() > 0)? (Income) budgetChange : (Expense) budgetChange;
    }

    public BudgetChangeResponseDto entityToResponseDto(BudgetChange entity) {
        return new BudgetChangeResponseDto(categoryMapper.entityToDto(entity.getCategory()), entity.getDateTime(), entity.getValue(), entity.getDescription());
    }


}
