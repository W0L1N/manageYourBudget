package com.kacwol.manageYourBudget.budgetreport.service;

import com.kacwol.manageYourBudget.budgetchange.model.BudgetChange;
import com.kacwol.manageYourBudget.budgetchange.model.Expense;
import com.kacwol.manageYourBudget.budgetchange.model.Income;
import com.kacwol.manageYourBudget.budgetchange.service.BudgetChangeService;
import com.kacwol.manageYourBudget.budgetreport.model.request.BudgetReportRequest;
import com.kacwol.manageYourBudget.budgetreport.model.response.BudgetReportElement;
import com.kacwol.manageYourBudget.budgetreport.model.response.BudgetReportResponse;
import com.kacwol.manageYourBudget.category.model.Category;
import com.kacwol.manageYourBudget.category.model.CategoryDto;
import com.kacwol.manageYourBudget.category.service.CategoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class BudgetReportService {

    private final CategoryService categoryService;

    private final BudgetChangeService budgetChangeService;

    public BudgetReportResponse makeReportResponse(BudgetReportRequest reportRequest) {

        List<Category> categories = categoryService.getAllCategories();

        List<Expense> expenses = budgetChangeService.getAllExpenses(
                reportRequest.getStartDate(),
                reportRequest.getEndDate()
        );

        LinkedList<Income> incomes = budgetChangeService.getAllIncomes(
                reportRequest.getStartDate(),
                reportRequest.getEndDate()
        );

        List<BudgetReportElement> elements = new ArrayList<>();

        for (Category category : categories) {
            log.info("income " + category);
            BigDecimal incomeSum = getValuesSum((LinkedList) incomes, category);
            log.info(" "+incomeSum.doubleValue());
            log.info("expense " + category);
            BigDecimal expenseSum = getValuesSum((LinkedList) expenses, category);
            log.info(" "+expenseSum.doubleValue());

            elements.add(
                    new BudgetReportElement(
                            new CategoryDto(
                                    category.getId(),
                                    category.getName()
                            ),
                            incomeSum,
                            expenseSum
                    )
            );
        }

        BigDecimal expense = elements.stream()
                .map(BudgetReportElement::getExpenseSum)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        log.info("Super expense: " + expense);

        BigDecimal income = elements.stream()
                .map(BudgetReportElement::getIncomeSum)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        log.info("Super income: " + income);

        BigDecimal sum = income.subtract(expense);

        return new BudgetReportResponse(reportRequest.getStartDate(), reportRequest.getEndDate(), expense, income, sum, elements);
    }

    public BudgetReportResponse makeReportResponseForYear(int year) {
        return makeReportResponse(
                new BudgetReportRequest(
                        LocalDate.of(year, 1, 1),
                        LocalDate.of(year, 12, 31)
                ));
    }

    public BudgetReportResponse makeReportResponseForMonth(int year, int month) {
        LocalDate date = LocalDate.of(year, month, 1);
        LocalDate lastDayOfMonthDate = date.withDayOfMonth(
                date.getMonth().length(date.isLeapYear()));

        return makeReportResponse(
                new BudgetReportRequest(
                        LocalDate.of(year, month, 1),
                        LocalDate.of(year, month, lastDayOfMonthDate.getDayOfMonth())
                ));
    }

    private BigDecimal getValuesSum(LinkedList<BudgetChange> elements, Category category) {
        BigDecimal sum = BigDecimal.valueOf(0);

        Iterator<BudgetChange> iterator = elements.listIterator();

        while(iterator.hasNext()){
            BudgetChange change = iterator.next();
            if(change.getCategory().equals(category)){
                //log.info(""+change.getValue().doubleValue() + "    " + category.getName());
                sum = sum.add(change.getValue());
                //log.info("sum: " + sum.doubleValue());
                iterator.remove();
            }
        }
        return sum;
    }
}
