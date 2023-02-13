package com.kacwol.manageYourBudget.ignore;

import com.kacwol.manageYourBudget.budgetchange.model.request.AllBudgetChangesByCategoryAndTimeDto;
import com.kacwol.manageYourBudget.budgetchange.model.BudgetChange;
import com.kacwol.manageYourBudget.budgetchange.model.request.BudgetChangeDto;
import com.kacwol.manageYourBudget.budgetchange.model.response.BudgetChangeResponseDto;
import com.kacwol.manageYourBudget.budgetchange.service.BudgetChangeServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/budgetChange")
public class BudgetChangeController {

    private final BudgetChangeServiceImpl budgetChangeService;

    @Autowired
    public BudgetChangeController(BudgetChangeServiceImpl budgetChangeService) {
        this.budgetChangeService = budgetChangeService;
    }

    @GetMapping("/{id}")
    public BudgetChangeResponseDto getById(Authentication authentication, @PathVariable Long id) {
        return budgetChangeService.getBudgetChangeById(authentication, id);
    }

    @GetMapping("/all")
    public List<BudgetChange> getAll(Authentication authentication) {
        return budgetChangeService.getAllBudgetChanges(authentication);
    }

    @GetMapping("/all/byCategory")
    public List<BudgetChange> getAllByCategory(Authentication authentication, @RequestParam Long categoryId) {
        return budgetChangeService.getAllByUserIdAndCategoryId(authentication, categoryId);
    }

    @GetMapping("/all/byTimeBetween/{userId}")
    public List<BudgetChange> getAllByCategoryAndTimeBetween(Authentication authentication, @RequestBody AllBudgetChangesByCategoryAndTimeDto dto) {
        return budgetChangeService.getAllByUserIdAndCategoryIdBetweenDates(authentication, dto);
    }

    @PostMapping
    public void addBudgetChange(Authentication authentication, @RequestBody BudgetChangeDto dto) {
        budgetChangeService.addBudgetChange(authentication, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(Authentication authentication, @PathVariable Long id) {
        budgetChangeService.deleteBudgetChangeById(authentication, id);
    }
}
