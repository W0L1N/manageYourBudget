package com.kacwol.manageYourBudget.category.service;

import com.kacwol.manageYourBudget.AuthService;
import com.kacwol.manageYourBudget.category.model.Category;
import com.kacwol.manageYourBudget.category.model.CategoryDto;
import com.kacwol.manageYourBudget.exception.CategoryNotFoundException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo categoryRepo;
    private final CategoryMapper mapper;

    private final AuthService authService;

    @Autowired
    public CategoryServiceImpl(CategoryRepo categoryRepo, CategoryMapper mapper, AuthService authService) {
        this.categoryRepo = categoryRepo;
        this.mapper = mapper;
        this.authService = authService;
    }

    @Override
    public void addCategory(Authentication authentication, String name) {
        Category category = mapper.dtoToEntity(authentication, name);
        categoryRepo.save(category);
    }

    @Override
    public Category getCategoryById(Authentication authentication, Long id) {
        return categoryRepo.findByIdAndUserId(id, authService.getId(authentication)).orElseThrow(CategoryNotFoundException::new);
    }

    @Override
    public CategoryDto getCategoryDtoById(Authentication authentication, Long id) {
        return mapper.entityToDto(getCategoryById(authentication, id));
    }

    @Override
    public void deleteCategoryById(Authentication authentication, Long id) {
        categoryRepo.deleteByIdAndUserId(id, authService.getId(authentication));
    }

    @Override
    public List<Category> getAllCategories(Authentication authentication) {
        return categoryRepo.findAllByUserId(authService.getId(authentication));
    }
}
