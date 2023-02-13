package com.kacwol.manageYourBudget.ignore;

import com.kacwol.manageYourBudget.budgetreport.model.request.BudgetReportRequest;
import com.kacwol.manageYourBudget.budgetreport.model.response.BudgetReportResponse;
import com.kacwol.manageYourBudget.budgetreport.service.BudgetReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/budgetReport")
public class BudgetReportController {

    private final BudgetReportService service;

    @Autowired
    public BudgetReportController(BudgetReportService service) {
        this.service = service;
    }

    @GetMapping
    public BudgetReportResponse getBudgetReport(Authentication auth, @RequestBody BudgetReportRequest request) {
        return service.makeReportResponse(auth, request);
    }

    @GetMapping("/byYear")
    public BudgetReportResponse getBudgetReport(Authentication auth, @RequestParam int year) {
        return service.makeReportResponseForYear(auth, year);
    }

    @GetMapping("/byMonth")
    public BudgetReportResponse getBudgetReport(Authentication auth, @RequestParam int year, @RequestParam int month) {
        return service.makeReportResponseForMonth(auth, year, month);
    }
}
