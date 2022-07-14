package com.kacwol.manageYourBudget.category.service;

import com.kacwol.manageYourBudget.User;
import com.kacwol.manageYourBudget.category.model.Category;
import com.kacwol.manageYourBudget.category.model.CategoryDto;
import com.kacwol.manageYourBudget.exception.CategoryNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo categoryRepo;
    private final CategoryMapper mapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepo categoryRepo, CategoryMapper mapper) {
        this.categoryRepo = categoryRepo;
        this.mapper = mapper;
    }

    @Override
    public void addCategory(CategoryDto dto, User user) {
        Category category = mapper.dtoToEntity(dto, user);
        categoryRepo.save(category);
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepo.findById(id).orElseThrow(CategoryNotFoundException::new);
    }

    @Override
    public CategoryDto getCategoryDtoById(Long id) {
        return mapper.entityToDto(getCategoryById(id));
    }

    @Override
    public void deleteCategoryById(Long id) {
        categoryRepo.deleteById(id);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }
}
