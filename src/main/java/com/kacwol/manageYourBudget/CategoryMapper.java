package com.kacwol.manageYourBudget;

import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public CategoryDto entityToDto(Category entity) {
        return new CategoryDto(entity.getName());
    }

    public Category dtoToEntity(CategoryDto dto, User user) {
        return new Category(dto.name, user);
    }

}
