package com.expensemanager.service;

import com.expensemanager.dto.BudgetRequestDTO;
import com.expensemanager.dto.BudgetSummaryDTO;
import com.expensemanager.entity.Budget;
import com.expensemanager.repository.BudgetRepository;
import com.expensemanager.repository.ExpenseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class BudgetService {

    private final BudgetRepository budgetRepository;
    private final ExpenseRepository expenseRepository;

    public BudgetService(BudgetRepository budgetRepository, ExpenseRepository expenseRepository) {
        this.budgetRepository = budgetRepository;
        this.expenseRepository = expenseRepository;
    }

    @Transactional(readOnly = true)
    public BudgetSummaryDTO getBudgetSummary(int month, int year) {
        String monthYear = String.format("%04d-%02d", year, month);
        Optional<Budget> budgetOpt = budgetRepository.findByMonthYear(monthYear);
        double budgetAmount = budgetOpt.map(Budget::getBudgetAmount).orElse(0.0);

        Double totalExpenses = expenseRepository.getMonthlyExpenseTotal(month, year);
        if (totalExpenses == null) {
            totalExpenses = 0.0;
        }

        double remaining = budgetAmount - totalExpenses;

        return new BudgetSummaryDTO(month, year, budgetAmount, totalExpenses, remaining);
    }

    public Budget setBudget(BudgetRequestDTO dto) {
        String monthYear = String.format("%04d-%02d", dto.getYear(), dto.getMonth());
        Optional<Budget> existingOpt = budgetRepository.findByMonthYear(monthYear);

        Budget budget;
        if (existingOpt.isPresent()) {
            budget = existingOpt.get();
            budget.setBudgetAmount(dto.getAmount());
        } else {
            budget = new Budget(monthYear, dto.getAmount());
        }

        return budgetRepository.save(budget);
    }
}
