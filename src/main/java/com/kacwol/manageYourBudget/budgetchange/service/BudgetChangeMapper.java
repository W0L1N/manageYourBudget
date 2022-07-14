package com.kacwol.manageYourBudget.budgetchange.service;

import com.kacwol.manageYourBudget.budgetchange.model.BudgetChange;
import com.kacwol.manageYourBudget.budgetchange.model.request.BudgetChangeDto;
import com.kacwol.manageYourBudget.budgetchange.model.response.BudgetChangeResponseDto;
import com.kacwol.manageYourBudget.category.model.Category;
import com.kacwol.manageYourBudget.category.service.CategoryMapper;
import org.springframework.stereotype.Component;

@Component
public class BudgetChangeMapper {

    private final CategoryMapper categoryMapper;

    public BudgetChangeMapper(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    public BudgetChange dtoToEntity(BudgetChangeDto dto, Category category) {
        return BudgetChange.builder()
                .category(category)
                .value(dto.getValue())
                .description(dto.getDescription())
                .dateTime(dto.getDateTime())
                .dateTime(dto.getDateTime())
                .build();
    }

    public BudgetChangeResponseDto entityToResponseDto(BudgetChange entity) {
        return new BudgetChangeResponseDto(categoryMapper.entityToDto(entity.getCategory()), entity.getDateTime(), entity.getValue(), entity.getDescription());
    }


}
