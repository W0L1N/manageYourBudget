package com.kacwol.manageYourBudget.category.service;

import com.kacwol.manageYourBudget.User;
import com.kacwol.manageYourBudget.category.model.Category;
import com.kacwol.manageYourBudget.category.model.CategoryDto;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public CategoryDto entityToDto(Category entity) {
        return new CategoryDto(entity.getName());
    }

    public Category dtoToEntity(CategoryDto dto, User user) {
        return new Category(dto.getName(), user);
    }

}
