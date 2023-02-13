package com.kacwol.manageYourBudget.ignore;

import com.kacwol.manageYourBudget.category.model.CategoryDto;
import com.kacwol.manageYourBudget.category.model.CategoryResponseDto;
import com.kacwol.manageYourBudget.category.service.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryServiceImpl categoryService;

    @Autowired
    public CategoryController(CategoryServiceImpl categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/{id}")
    public CategoryDto getById(Authentication authentication, @PathVariable Long id) {
        return categoryService.getCategoryDtoById(authentication, id);
    }

    @GetMapping
    public List<CategoryResponseDto> getAll(Authentication authentication) {
        return categoryService.getAllCategoryResponses(authentication);
    }

    @PostMapping
    public void addCategory(Authentication authentication, @RequestParam String name) {
        categoryService.addCategory(authentication, name);
    }
}
