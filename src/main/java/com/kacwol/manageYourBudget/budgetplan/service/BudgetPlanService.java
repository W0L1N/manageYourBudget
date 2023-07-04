package com.kacwol.manageYourBudget.budgetplan.service;

import com.kacwol.manageYourBudget.budgetplan.model.BudgetPlan;
import com.kacwol.manageYourBudget.budgetplan.model.BudgetPlanElement;
import com.kacwol.manageYourBudget.budgetplan.model.request.BudgetPlanRequest;
import com.kacwol.manageYourBudget.budgetplan.model.response.BudgetPlanElementResponseDto;
import com.kacwol.manageYourBudget.budgetplan.model.response.BudgetPlanResponseDto;
import com.kacwol.manageYourBudget.budgetreport.model.response.BudgetReportElement;
import com.kacwol.manageYourBudget.budgetreport.model.response.BudgetReportResponse;
import com.kacwol.manageYourBudget.budgetreport.service.BudgetReportService;
import com.kacwol.manageYourBudget.exception.BudgetPlanNotFoundException;
import com.kacwol.manageYourBudget.user.model.User;
import com.kacwol.manageYourBudget.user.service.AuthService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class BudgetPlanService {

    private final BudgetPlanRepo planRepo;

    private final BudgetPlanElementRepo elementRepo;

    private final BudgetPlanMapper mapper;

    private final AuthService authService;

    private final BudgetReportService budgetReportService;



    public BudgetPlan getById(Long id) {
        return planRepo.findByIdAndUserId(id, authService.getId()).orElseThrow(BudgetPlanNotFoundException::new);
    }

    public BudgetPlanResponseDto getBudgetPlanReport(Long id) {

        BudgetPlan plan = getById(id);
        BudgetReportResponse budgetReport = budgetReportService.makeReportResponseForMonth(plan.getYear(), plan.getMonth());
        List<BudgetPlanElementResponseDto> planElements = new ArrayList<>();

        for (BudgetPlanElement element : plan.getElements()) {
            BudgetReportElement reportElement = getBudgetReportElement(
                    budgetReport.getElementList(),
                    element
            );

            planElements.add(
                    mapper.planElementToDto(
                            reportElement,
                            element
                    )
            );
        }
        return new BudgetPlanResponseDto(plan.getYear(), plan.getMonth(), planElements);
    }

    public void addBudgetPlan(BudgetPlanRequest request) {

        List<BudgetPlanElement> elements = request.getElements()
                .stream()
                .map(mapper::elementToEntity)
                .map(elementRepo::save)
                .toList();
        User user = authService.getAuthenticatedUser();
        planRepo.save(new BudgetPlan(null, user, elements, request.getYear(), request.getMonth()));
    }


    private BudgetReportElement getBudgetReportElement (List<BudgetReportElement> reportElements, BudgetPlanElement element) {
        return reportElements
                .stream()
                .filter(
                        e -> Objects.equals(
                                e.getCategory().getId(),
                                element.getCategory().getId()
                        )
                )
                .findFirst()
                .orElseThrow();
    }
}
