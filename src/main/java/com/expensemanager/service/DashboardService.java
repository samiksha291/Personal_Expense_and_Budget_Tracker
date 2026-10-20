package com.expensemanager.service;

import com.expensemanager.dto.DashboardSummaryDTO;
import com.expensemanager.dto.MonthlySummaryDTO;
import com.expensemanager.repository.ExpenseRepository;
import com.expensemanager.repository.IncomeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class DashboardService {

    private final IncomeRepository incomeRepository;
    private final ExpenseRepository expenseRepository;

    public DashboardService(IncomeRepository incomeRepository, ExpenseRepository expenseRepository) {
        this.incomeRepository = incomeRepository;
        this.expenseRepository = expenseRepository;
    }

    public DashboardSummaryDTO getDashboardSummary() {
        Double totalIncome = incomeRepository.getTotalIncome();
        Double totalExpenses = expenseRepository.getTotalExpenses();

        if (totalIncome == null) totalIncome = 0.0;
        if (totalExpenses == null) totalExpenses = 0.0;

        double remaining = totalIncome - totalExpenses;
        return new DashboardSummaryDTO(totalIncome, totalExpenses, remaining);
    }

    public MonthlySummaryDTO getMonthlySummary() {
        Double totalIncome = incomeRepository.getTotalIncome();
        Double totalExpenses = expenseRepository.getTotalExpenses();
        String highestCategory = expenseRepository.getHighestExpenseCategory();

        if (totalIncome == null) totalIncome = 0.0;
        if (totalExpenses == null) totalExpenses = 0.0;
        if (highestCategory == null) highestCategory = "None";

        double remaining = totalIncome - totalExpenses;
        return new MonthlySummaryDTO(totalIncome, totalExpenses, highestCategory, remaining);
    }
}
