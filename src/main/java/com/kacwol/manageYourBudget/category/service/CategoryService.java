package com.kacwol.manageYourBudget.category.service;

import com.kacwol.manageYourBudget.category.model.Category;
import com.kacwol.manageYourBudget.category.model.CategoryDto;
import com.kacwol.manageYourBudget.category.model.CategoryResponseDto;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface CategoryService {

    void addCategory(Authentication authentication, String name);

    Category getCategoryById(Authentication authentication, Long id);

    CategoryDto getCategoryDtoById(Authentication authentication, Long id);

    void deleteCategoryById(Authentication authentication, Long id);

    List<Category> getAllCategories(Authentication authentication);

    List<CategoryResponseDto> getAllCategoryResponses(Authentication authentication);
}
