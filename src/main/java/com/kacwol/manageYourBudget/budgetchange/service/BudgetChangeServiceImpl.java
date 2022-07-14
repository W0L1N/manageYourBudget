package com.kacwol.manageYourBudget.budgetchange.service;

import com.kacwol.manageYourBudget.budgetchange.BudgetChangeRepo;
import com.kacwol.manageYourBudget.budgetchange.model.BudgetChange;
import com.kacwol.manageYourBudget.budgetchange.model.request.AllBudgetChangesByCategoryAndTimeDto;
import com.kacwol.manageYourBudget.budgetchange.model.request.BudgetChangeDto;
import com.kacwol.manageYourBudget.budgetchange.model.response.BudgetChangeResponseDto;
import com.kacwol.manageYourBudget.category.model.Category;
import com.kacwol.manageYourBudget.category.service.CategoryServiceImpl;
import com.kacwol.manageYourBudget.exception.BudgetChangeNotFoundException;

import java.time.LocalDate;
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
        BudgetChange change = budgetChangeRepo.findById(id).orElseThrow(BudgetChangeNotFoundException::new);
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

    @Override
    public List<BudgetChange> getAllByUserIdAndCategoryId(Long userId, Long categoryId) {
        return budgetChangeRepo.findAllByUserIdAndCategoryId(userId, categoryId);
    }

    @Override
    public List<BudgetChange> getAllByUserIdAndCategoryIdBetweenDates(Long userId, AllBudgetChangesByCategoryAndTimeDto dto) {
        return budgetChangeRepo.findAllByUserIdAndCategoryIdAndDateTimeBetween(userId, dto.getCategoryId(), dto.getStart(), dto.getEnd());
    }

    @Override
    public List<BudgetChange> getAllByUserIdBetweenDates(Long userId, LocalDate start, LocalDate end) {
        return budgetChangeRepo.findAllByUserIdAndDateTimeBetween(userId, start, end);
    }
}
