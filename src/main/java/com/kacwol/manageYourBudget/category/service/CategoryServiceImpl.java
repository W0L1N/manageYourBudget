package com.kacwol.manageYourBudget.category.service;

import com.kacwol.manageYourBudget.category.model.Category;
import com.kacwol.manageYourBudget.category.model.CategoryDto;
import com.kacwol.manageYourBudget.category.model.CategoryResponseDto;
import com.kacwol.manageYourBudget.exception.CategoryNotFoundException;
import com.kacwol.manageYourBudget.user.service.AuthService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo categoryRepo;
    private final CategoryMapper mapper;

    private final AuthService authService;

    @Override
    public void addCategory(String name) {
        Category category = mapper.dtoToEntity(name);
        categoryRepo.save(category);
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepo.findByIdAndUserId(id, authService.getId()).orElseThrow(CategoryNotFoundException::new);
    }

    @Override
    public CategoryDto getCategoryDtoById(Long id) {
        return mapper.entityToDto(getCategoryById(id));
    }

    @Override
    public void deleteCategoryById(Long id) {
        categoryRepo.deleteByIdAndUserId(id, authService.getId());
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepo.findAllByUserId(authService.getId());
    }

    @Override
    public List<CategoryResponseDto> getAllCategoryResponses() {
        return getAllCategories()
                .stream()
                .map(mapper::entityToResponse)
                .toList();
    }
}
