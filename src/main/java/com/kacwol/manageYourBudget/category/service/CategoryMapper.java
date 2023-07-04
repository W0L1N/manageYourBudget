package com.kacwol.manageYourBudget.category.service;

import com.kacwol.manageYourBudget.category.model.Category;
import com.kacwol.manageYourBudget.category.model.CategoryDto;
import com.kacwol.manageYourBudget.category.model.CategoryResponseDto;
import com.kacwol.manageYourBudget.user.service.AuthService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class CategoryMapper {

    private final AuthService userService;

    public CategoryDto entityToDto(Category entity) {
        return new CategoryDto(entity.getId(), entity.getName());
    }

    public Category dtoToEntity(String name) {
        return new Category(name, userService.getAuthenticatedUser());
    }

    public CategoryResponseDto entityToResponse(Category entity) {
        return new CategoryResponseDto(entity.getId(), entity.getName(), entity.getUser().getId());
    }

}
