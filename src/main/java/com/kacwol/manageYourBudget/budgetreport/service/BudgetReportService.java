package com.kacwol.manageYourBudget.budgetreport.service;

import com.kacwol.manageYourBudget.budgetchange.model.BudgetChange;
import com.kacwol.manageYourBudget.budgetchange.service.BudgetChangeServiceImpl;
import com.kacwol.manageYourBudget.budgetreport.model.request.BudgetReportRequest;
import com.kacwol.manageYourBudget.budgetreport.model.response.BudgetReportElement;
import com.kacwol.manageYourBudget.budgetreport.model.response.BudgetReportResponse;
import com.kacwol.manageYourBudget.category.model.Category;
import com.kacwol.manageYourBudget.category.model.CategoryDto;
import com.kacwol.manageYourBudget.category.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

@Service
public class BudgetReportService {

    private final CategoryService categoryService;

    private final BudgetChangeServiceImpl budgetChangeService;

    @Autowired
    public BudgetReportService(CategoryService categoryService, BudgetChangeServiceImpl budgetChangeService) {
        this.categoryService = categoryService;
        this.budgetChangeService = budgetChangeService;
    }

    public BudgetReportResponse makeReportResponse(Authentication auth, BudgetReportRequest reportRequest) {

        List<Category> categories = categoryService.getAllCategories(auth);

        List<BudgetChange> budgetChanges = budgetChangeService.getAllBudgetChanges(
                auth,
                reportRequest.getStartDate(),
                reportRequest.getEndDate()
        );

        List<BudgetReportElement> elements = new ArrayList<>();

        for (Category category : categories) {
            List<Double> values = budgetChanges
                    .stream()
                    .filter(budgetChange -> Objects.equals(budgetChange.getCategory().getId(), category.getId()))
                    .map(BudgetChange::getValue)
                    .toList();

            double incomeSum = getValuesSum(values, value -> value > 0);
            double expenseSum = getValuesSum(values, value -> value < 0);

            elements.add(
                    new BudgetReportElement(
                            new CategoryDto(
                                    category.getName()
                            ),
                            incomeSum,
                            expenseSum
                    )
            );
        }

        double expense = elements.stream()
                .mapToDouble(BudgetReportElement::getExpenseSum)
                .sum();

        double income = elements.stream()
                .mapToDouble(BudgetReportElement::getIncomeSum)
                .sum();

        double sum = income + expense;

        return new BudgetReportResponse(reportRequest.getStartDate(), reportRequest.getEndDate(), expense, income, sum, elements);
    }

    public BudgetReportResponse makeReportResponseForYear(Authentication auth, int year) {
        return makeReportResponse(
                auth,
                new BudgetReportRequest(
                        LocalDate.of(year, 1, 1),
                        LocalDate.of(year, 12, 31)
                ));
    }

    public BudgetReportResponse makeReportResponseForMonth(Authentication auth, int year, int month) {
        LocalDate date = LocalDate.of(year, month, 1);
        LocalDate lastDayOfMonthDate = date.withDayOfMonth(
                date.getMonth().length(date.isLeapYear()));

        return makeReportResponse(
                auth,
                new BudgetReportRequest(
                        LocalDate.of(year, month, 1),
                        LocalDate.of(year, month, lastDayOfMonthDate.getDayOfMonth())
                ));
    }

    private double getValuesSum(List<Double> elements, Predicate<Double> testValue) {
        return elements
                .stream()
                .filter(testValue)
                .mapToDouble(e -> e)
                .sum();
    }
}
