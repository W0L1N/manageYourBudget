package com.kacwol.manageYourBudget.budgetplan.service;

import com.kacwol.manageYourBudget.AuthService;
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
import com.kacwol.manageYourBudget.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class BudgetPlanService {

    private final BudgetPlanRepo planRepo;

    private final BudgetPlanElementRepo elementRepo;

    private final BudgetPlanMapper mapper;
    private final AuthService authService;

    private final UserService userService;

    private final BudgetReportService budgetReportService;


    @Autowired
    public BudgetPlanService(BudgetPlanRepo planRepo, BudgetPlanElementRepo elementRepo, BudgetPlanMapper mapper, AuthService authService, UserService userService, BudgetReportService budgetReportService) {
        this.planRepo = planRepo;
        this.elementRepo = elementRepo;
        this.mapper = mapper;
        this.authService = authService;
        this.userService = userService;
        this.budgetReportService = budgetReportService;
    }

    public BudgetPlan getById(Authentication auth, Long id) {
        return planRepo.findByIdAndUserId(id, authService.getId(auth)).orElseThrow(BudgetPlanNotFoundException::new);
    }

    public BudgetPlanResponseDto getBudgetPlanReport(Authentication auth, Long id) {

        BudgetPlan plan = getById(auth, id);
        BudgetReportResponse budgetReport = budgetReportService.makeReportResponseForMonth(auth, plan.getYear(), plan.getMonth());
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

    public void addBudgetPlan(Authentication auth, BudgetPlanRequest request) {

//        for (BudgetPlanRequestElement element : request.getElements()) {
//            if(element.getValue() > 0) {
//                throw new BadValueException("Value cannot be higher than 0 ");
//            }
//        }

        List<BudgetPlanElement> elements = request.getElements()
                .stream()
                .map(w -> mapper.elementToEntity(auth, w))
                .map(elementRepo::save)
                .toList();
        User user = userService.getByUserName(auth.getName());
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
