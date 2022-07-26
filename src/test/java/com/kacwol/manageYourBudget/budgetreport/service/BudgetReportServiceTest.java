package com.kacwol.manageYourBudget.budgetreport.service;

import com.kacwol.manageYourBudget.budgetchange.model.BudgetChange;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@WithMockUser
public class BudgetReportServiceTest {

    @Mock
    private CategoryService categoryService;

    @Mock
    private BudgetChangeServiceImpl budgetChangeService;

    @InjectMocks
    private BudgetReportService budgetReportService;

    @Test
    public void test() {

        LocalDate start = LocalDate.of(2002, 7, 4);
        LocalDate end = LocalDate.of(2022, 7, 22);

        LocalDate date = LocalDate.of(2013, 5, 5);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = new User(1L, "user", "passwd", Set.of("ROLE_USER"));

        Category shopping = new Category(1L, "shopping", user);
        Category insurance = new Category(2L, "insurance", user);
        Category job = new Category(3L, "job", user);

        BudgetChange food = new BudgetChange(1L, user, shopping, date, -150, "food");
        BudgetChange clothes = new BudgetChange(1L, user, shopping, date, -500, "clothes");
        BudgetChange gift = new BudgetChange(1L, user, shopping, date, -230, "gift for child");

        BudgetChange forCar = new BudgetChange(1L, user, insurance, date, -170, "OC/AC");
        BudgetChange forBrokenLeg = new BudgetChange(1L, user, insurance, date, 300, "for broken leg");

        BudgetChange salary = new BudgetChange(1L, user, job, date, 3500, "salary");
        BudgetChange anotherSalary = new BudgetChange(1L, user, job, date, 3500, "another salary");

        BudgetReportElement forShopping = new BudgetReportElement(new CategoryDto(shopping.getName()), 0, food.getValue() + clothes.getValue() + gift.getValue());
        BudgetReportElement forInsurance = new BudgetReportElement(new CategoryDto(insurance.getName()), forBrokenLeg.getValue(), forCar.getValue());
        BudgetReportElement forJob = new BudgetReportElement(new CategoryDto(job.getName()), salary.getValue() + anotherSalary.getValue(), 0);

        double expenses = food.getValue() + clothes.getValue() + gift.getValue() + forCar.getValue();
        double incomes = forBrokenLeg.getValue() + salary.getValue() + anotherSalary.getValue();
        double sum = expenses + incomes;

        BudgetReportResponse expected = new BudgetReportResponse(start, end, expenses, incomes, sum, new ArrayList<>(List.of(forShopping, forInsurance, forJob)));


        Mockito.when(categoryService.getAllCategories(auth))
                .thenReturn(List.of(shopping, insurance, job));

        Mockito.when(budgetChangeService.getAllBudgetChanges(auth, start, end))
                .thenReturn(List.of(food, clothes, gift, forCar, forBrokenLeg, salary, anotherSalary));


        BudgetReportResponse actual = budgetReportService.makeReportResponse(auth, new BudgetReportRequest(start, end));
        Assert.assertEquals(expected, actual);

        Assert.assertEquals(expected.getStartDate(), actual.getStartDate());
        Assert.assertEquals(expected.getEndDate(), actual.getEndDate());

        Assert.assertEquals(expected.getSum(), actual.getSum(), 0);
        Assert.assertEquals(expected.getExpenseSum(), actual.getExpenseSum(), 0);
        Assert.assertEquals(expected.getElementList(), actual.getElementList());


    }

}
