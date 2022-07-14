package com.kacwol.manageYourBudget.category.service;

import com.kacwol.manageYourBudget.User;
import com.kacwol.manageYourBudget.category.model.Category;
import com.kacwol.manageYourBudget.category.model.CategoryDto;
import java.util.List;

public interface CategoryService {

    void addCategory(CategoryDto category, User user);

    Category getCategoryById(Long id);

    CategoryDto getCategoryDtoById(Long id);

    void deleteCategoryById(Long id);

    List<Category> getAllCategories();
}
