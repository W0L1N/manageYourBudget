package com.kacwol.manageYourBudget.category.service;

import com.kacwol.manageYourBudget.category.model.Category;
import com.kacwol.manageYourBudget.category.model.CategoryDto;
import com.kacwol.manageYourBudget.category.model.CategoryResponseDto;
import com.kacwol.manageYourBudget.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    private final UserService userService;

    @Autowired
    public CategoryMapper(UserService userService) {
        this.userService = userService;
    }

    public CategoryDto entityToDto(Category entity) {
        return new CategoryDto(entity.getName());
    }

    public Category dtoToEntity(Authentication authentication, String name) {
        return new Category(name, userService.getByUserName(authentication.getName()));
    }

    public CategoryResponseDto entityToResponse(Category entity) {
        return new CategoryResponseDto(entity.getId(), entity.getName(), entity.getUser().getId());
    }

}
