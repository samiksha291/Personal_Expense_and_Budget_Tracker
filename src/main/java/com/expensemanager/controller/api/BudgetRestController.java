package com.expensemanager.controller.api;

import com.expensemanager.dto.BudgetRequestDTO;
import com.expensemanager.dto.BudgetSummaryDTO;
import com.expensemanager.entity.Budget;
import com.expensemanager.service.BudgetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/budget")
@CrossOrigin(origins = "*")
public class BudgetRestController {

    private final BudgetService budgetService;

    public BudgetRestController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @GetMapping
    public ResponseEntity<BudgetSummaryDTO> getBudget(
            @RequestParam(required = false) Integer month,
            @RequestParam(required = false) Integer year) {

        LocalDate now = LocalDate.now();
        int targetMonth = (month != null) ? month : now.getMonthValue();
        int targetYear = (year != null) ? year : now.getYear();

        BudgetSummaryDTO summary = budgetService.getBudgetSummary(targetMonth, targetYear);
        return ResponseEntity.ok(summary);
    }

    @PostMapping
    public ResponseEntity<Budget> setBudget(@RequestBody BudgetRequestDTO dto) {
        Budget saved = budgetService.setBudget(dto);
        return ResponseEntity.ok(saved);
    }
}
