package com.kacwol.manageYourBudget;

public interface CategoryService {

    void addCategory(CategoryDto category, User user);

    CategoryDto getCategoryById(Long id);

    void deleteCategoryById(Long id);
}
