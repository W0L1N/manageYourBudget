package com.kacwol.manageYourBudget.category.service;

import com.kacwol.manageYourBudget.User;
import com.kacwol.manageYourBudget.category.model.Category;
import com.kacwol.manageYourBudget.category.model.CategoryDto;

public interface CategoryService {

    void addCategory(CategoryDto category, User user);

    Category getCategoryById(Long id);

    void deleteCategoryById(Long id);
}
