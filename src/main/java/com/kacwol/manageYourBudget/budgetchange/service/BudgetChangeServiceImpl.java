package com.kacwol.manageYourBudget.budgetchange.service;

import com.kacwol.manageYourBudget.budgetchange.BudgetChangeRepo;
import com.kacwol.manageYourBudget.budgetchange.model.BudgetChange;
import com.kacwol.manageYourBudget.budgetchange.model.BudgetChangeDto;
import com.kacwol.manageYourBudget.budgetchange.model.BudgetChangeResponseDto;
import com.kacwol.manageYourBudget.category.model.Category;
import com.kacwol.manageYourBudget.category.service.CategoryServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BudgetChangeServiceImpl implements BudgetChangeService {

    private final BudgetChangeRepo budgetChangeRepo;
    private final BudgetChangeMapper mapper;

    private final CategoryServiceImpl categoryService;

    @Autowired
    public BudgetChangeServiceImpl(BudgetChangeRepo budgetChangeRepo, BudgetChangeMapper mapper, CategoryServiceImpl categoryService) {
        this.budgetChangeRepo = budgetChangeRepo;
        this.mapper = mapper;
        this.categoryService = categoryService;
    }

    @Override
    public void addBudgetChange(BudgetChangeDto budgetChange) {
        Category category = categoryService.getCategoryById(budgetChange.getCategoryId());
        budgetChangeRepo.save(mapper.dtoToEntity(budgetChange, category));
    }

    @Override
    public BudgetChangeResponseDto getBudgetChangeById(Long id) {
        BudgetChange change = budgetChangeRepo.findById(id).orElseThrow();
        return mapper.entityToResponseDto(change);
    }

    @Override
    public void deleteBudgetChangeById(Long id) {
        budgetChangeRepo.deleteById(id);
    }

    @Override
    public List<BudgetChange> getAllBudgetChanges() {
        return budgetChangeRepo.findAll();
    }
}
