package com.kacwol.manageYourBudget.category.service;

import com.kacwol.manageYourBudget.category.model.Category;
import com.kacwol.manageYourBudget.category.model.CategoryDto;
import com.kacwol.manageYourBudget.category.model.CategoryResponseDto;

import java.util.List;

public interface CategoryService {

    void addCategory(String name);

    Category getCategoryById(Long id);

    CategoryDto getCategoryDtoById(Long id);

    void deleteCategoryById(Long id);

    List<Category> getAllCategories();

    List<CategoryResponseDto> getAllCategoryResponses();
}
