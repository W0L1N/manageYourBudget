package com.kacwol.manageYourBudget.budgetchange.service;

import com.kacwol.manageYourBudget.budgetchange.BudgetChangeRepo;
import com.kacwol.manageYourBudget.budgetchange.ExpenseRepo;
import com.kacwol.manageYourBudget.budgetchange.IncomeRepo;
import com.kacwol.manageYourBudget.budgetchange.model.BudgetChange;
import com.kacwol.manageYourBudget.budgetchange.model.Expense;
import com.kacwol.manageYourBudget.budgetchange.model.Income;
import com.kacwol.manageYourBudget.budgetchange.model.request.AllBudgetChangesByCategoryAndTimeDto;
import com.kacwol.manageYourBudget.budgetchange.model.request.BudgetChangeDto;
import com.kacwol.manageYourBudget.budgetchange.model.response.BudgetChangeResponseDto;
import com.kacwol.manageYourBudget.category.model.Category;
import com.kacwol.manageYourBudget.category.service.CategoryServiceImpl;
import com.kacwol.manageYourBudget.exception.BadDatesException;
import com.kacwol.manageYourBudget.exception.BudgetChangeNotFoundException;
import com.kacwol.manageYourBudget.user.service.AuthService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class BudgetChangeServiceImpl implements BudgetChangeService {

    private final BudgetChangeRepo budgetChangeRepo;

    private final ExpenseRepo expenseRepo;

    private final IncomeRepo incomeRepo;

    private final BudgetChangeMapper mapper;

    private final CategoryServiceImpl categoryService;

    private final AuthService authService;


    @Override
    public void addBudgetChange(BudgetChangeDto budgetChange) {
        Category category = categoryService.getCategoryById(budgetChange.getCategoryId());
        budgetChangeRepo.save(mapper.dtoToEntity(budgetChange, category));
    }

    @Override
    public BudgetChangeResponseDto getBudgetChangeById(Long id) {
        BudgetChange change = budgetChangeRepo.findByIdAndUserId(id, authService.getId()).orElseThrow(BudgetChangeNotFoundException::new);
        return mapper.entityToResponseDto(change);
    }

    @Override
    public void deleteBudgetChangeById(Long id) {
        budgetChangeRepo.deleteByIdAndUserId(id, authService.getId());
    }

    @Override
    public List<BudgetChange> getAllBudgetChanges() {
        return budgetChangeRepo.findAllByUserId(authService.getId());
    }

    @Override
    public List<BudgetChange> getAllBudgetChanges(LocalDate startDate, LocalDate endDate) {
        if (startDate.isAfter(endDate)) {
            throw new BadDatesException("Start date cannot be later than end date.");
        } else {
            return budgetChangeRepo.findAllByUserIdAndDateTimeBetween(authService.getId(), startDate, endDate);
        }
    }

    @Override
    public LinkedList<Expense> getAllExpenses(LocalDate startDate, LocalDate endDate) {
        if (startDate.isAfter(endDate)) {
            throw new BadDatesException("Start date cannot be later than end date.");
        } else {
            return expenseRepo.findAllByUserIdAndDateTimeBetween(authService.getId(), startDate, endDate);
        }
    }

    @Override
    public LinkedList<Income> getAllIncomes(LocalDate startDate, LocalDate endDate) {
        if (startDate.isAfter(endDate)) {
            throw new BadDatesException("Start date cannot be later than end date.");
        } else {
            return incomeRepo.findAllByUserIdAndDateTimeBetween(authService.getId(), startDate, endDate);
        }
    }

    @Override
    public List<BudgetChange> getAllByUserIdAndCategoryId(Long categoryId) {
        return budgetChangeRepo.findAllByUserIdAndCategoryId(authService.getId(), categoryId);
    }

    @Override
    public List<BudgetChange> getAllByUserIdAndCategoryIdBetweenDates(AllBudgetChangesByCategoryAndTimeDto dto) {
        if (dto.getStart().isAfter(dto.getEnd())) {
            throw new BadDatesException("Start date cannot be later than end date.");
        } else {
            return budgetChangeRepo.findAllByUserIdAndCategoryIdAndDateTimeBetween(authService.getId(), dto.getCategoryId(), dto.getStart(), dto.getEnd());
        }
    }
}
