package com.kacwol.manageYourBudget.budgetreport.service;

import com.kacwol.manageYourBudget.budgetchange.model.Expense;
import com.kacwol.manageYourBudget.budgetchange.model.Income;
import com.kacwol.manageYourBudget.budgetchange.service.BudgetChangeServiceImpl;
import com.kacwol.manageYourBudget.budgetreport.model.request.BudgetReportRequest;
import com.kacwol.manageYourBudget.budgetreport.model.response.BudgetReportElement;
import com.kacwol.manageYourBudget.budgetreport.model.response.BudgetReportResponse;
import com.kacwol.manageYourBudget.category.model.Category;
import com.kacwol.manageYourBudget.category.model.CategoryDto;
import com.kacwol.manageYourBudget.category.service.CategoryService;
import com.kacwol.manageYourBudget.user.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@WithMockUser
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class BudgetReportServiceTest {

    @Mock
    private CategoryService categoryService;

    @Mock
    private BudgetChangeServiceImpl budgetChangeService;

    @InjectMocks
    private BudgetReportService budgetReportService;

    @Test
    public void test() {
    //given
        LocalDate start = LocalDate.of(2002, 7, 4);
        LocalDate end = LocalDate.of(2022, 7, 22);

        LocalDate date = LocalDate.of(2013, 5, 5);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = new User(1L, "user", "passwd", Set.of("ROLE_USER"));

        Category shopping = new Category(1L, "shopping", user);
        Category insurance = new Category(2L, "insurance", user);
        Category job = new Category(3L, "job", user);

        Expense food = new Expense(1L, user, shopping, date, BigDecimal.valueOf(150), "food");
        Expense clothes = new Expense(2L, user, shopping, date, BigDecimal.valueOf(500), "clothes");
        Expense gift = new Expense(3L, user, shopping, date, BigDecimal.valueOf(230), "gift for child");

        Expense forCar = new Expense(4L, user, insurance, date, BigDecimal.valueOf(170), "OC/AC");
        Income forBrokenLeg = new Income(5L, user, insurance, date, BigDecimal.valueOf(300), "for broken leg");

        Income salary = new Income(6L, user, job, date, BigDecimal.valueOf(3500), "salary");
        Income anotherSalary = new Income(7L, user, job, date, BigDecimal.valueOf(3500), "another salary");

        BudgetReportElement forShopping = new BudgetReportElement(new CategoryDto(1L, shopping.getName()), BigDecimal.ZERO, food.getValue().add(clothes.getValue()).add(gift.getValue()));
        BudgetReportElement forInsurance = new BudgetReportElement(new CategoryDto(2L, insurance.getName()), forBrokenLeg.getValue(), forCar.getValue());
        BudgetReportElement forJob = new BudgetReportElement(new CategoryDto(3L, job.getName()), salary.getValue().add(anotherSalary.getValue()), BigDecimal.ZERO);

        BigDecimal expenses = food.getValue().add(clothes.getValue()).add(gift.getValue()).add(forCar.getValue());
        BigDecimal incomes = forBrokenLeg.getValue().add(salary.getValue()).add(anotherSalary.getValue());
        BigDecimal sum = incomes.subtract(expenses);

        BudgetReportResponse expected = new BudgetReportResponse(start, end, expenses, incomes, sum, new ArrayList<>(List.of(forShopping, forInsurance, forJob)));

        //when
        Mockito.when(categoryService.getAllCategories(auth))
                .thenReturn(List.of(shopping, insurance, job));

        Mockito.when(budgetChangeService.getAllExpenses(auth, start, end))
                        .thenReturn(new LinkedList<>(List.of(food, clothes, gift, forCar)));

        Mockito.when(budgetChangeService.getAllIncomes(auth, start, end))
                .thenReturn( new LinkedList<>(List.of(forBrokenLeg, salary, anotherSalary)));

        //then

        BudgetReportResponse actual = budgetReportService.makeReportResponse(auth, new BudgetReportRequest(start, end));

        Assert.assertEquals(expected, actual);
    }

}
