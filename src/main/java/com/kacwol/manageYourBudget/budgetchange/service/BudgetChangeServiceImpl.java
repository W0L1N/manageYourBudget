package com.kacwol.manageYourBudget.budgetchange.service;

import com.kacwol.manageYourBudget.AuthService;
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
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
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
    public void addBudgetChange(Authentication authentication, BudgetChangeDto budgetChange) {
        Category category = categoryService.getCategoryById(authentication, budgetChange.getCategoryId());
        budgetChangeRepo.save(mapper.dtoToEntity(budgetChange, category));
    }

    @Override
    public BudgetChangeResponseDto getBudgetChangeById(Authentication authentication, Long id) {
        BudgetChange change = budgetChangeRepo.findByIdAndUserId(id, authService.getId(authentication)).orElseThrow(BudgetChangeNotFoundException::new);
        return mapper.entityToResponseDto(change);
    }

    @Override
    public void deleteBudgetChangeById(Authentication authentication, Long id) {
        budgetChangeRepo.deleteByIdAndUserId(id, authService.getId(authentication));
    }

    @Override
    public List<BudgetChange> getAllBudgetChanges(Authentication authentication) {
        return budgetChangeRepo.findAllByUserId(authService.getId(authentication));
    }

    @Override
    public List<BudgetChange> getAllBudgetChanges(Authentication auth, LocalDate startDate, LocalDate endDate) {
        if (startDate.isAfter(endDate)) {
            throw new BadDatesException("Start date cannot be later than end date.");
        } else {
            return budgetChangeRepo.findAllByUserIdAndDateTimeBetween(authService.getId(auth), startDate, endDate);
        }
    }

    @Override
    public LinkedList<Expense> getAllExpenses(Authentication auth, LocalDate startDate, LocalDate endDate) {
        if (startDate.isAfter(endDate)) {
            throw new BadDatesException("Start date cannot be later than end date.");
        } else {
            return expenseRepo.findAllByUserIdAndDateTimeBetween(authService.getId(auth), startDate, endDate);
        }
    }

    @Override
    public LinkedList<Income> getAllIncomes(Authentication auth, LocalDate startDate, LocalDate endDate) {
        if (startDate.isAfter(endDate)) {
            throw new BadDatesException("Start date cannot be later than end date.");
        } else {
            return incomeRepo.findAllByUserIdAndDateTimeBetween(authService.getId(auth), startDate, endDate);
        }
    }

    @Override
    public List<BudgetChange> getAllByUserIdAndCategoryId(Authentication authentication, Long categoryId) {
        return budgetChangeRepo.findAllByUserIdAndCategoryId(authService.getId(authentication), categoryId);
    }

    @Override
    public List<BudgetChange> getAllByUserIdAndCategoryIdBetweenDates(Authentication authentication, AllBudgetChangesByCategoryAndTimeDto dto) {
        if (dto.getStart().isAfter(dto.getEnd())) {
            throw new BadDatesException("Start date cannot be later than end date.");
        } else {
            return budgetChangeRepo.findAllByUserIdAndCategoryIdAndDateTimeBetween(authService.getId(authentication), dto.getCategoryId(), dto.getStart(), dto.getEnd());
        }
    }
}
