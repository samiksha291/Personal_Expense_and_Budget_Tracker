package com.expensemanager.service;

import com.expensemanager.dto.TransactionDTO;
import com.expensemanager.entity.Expense;
import com.expensemanager.entity.Income;
import com.expensemanager.repository.ExpenseRepository;
import com.expensemanager.repository.IncomeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class TransactionService {

    private final IncomeRepository incomeRepository;
    private final ExpenseRepository expenseRepository;

    public TransactionService(IncomeRepository incomeRepository, ExpenseRepository expenseRepository) {
        this.incomeRepository = incomeRepository;
        this.expenseRepository = expenseRepository;
    }

    public List<TransactionDTO> getAllTransactions() {
        List<Income> incomes = incomeRepository.findAll();
        List<Expense> expenses = expenseRepository.findAll();

        List<TransactionDTO> transactions = new ArrayList<>();

        for (Income inc : incomes) {
            transactions.add(new TransactionDTO(
                    inc.getIncomeDate(),
                    "Income",
                    inc.getSource(),
                    inc.getDescription() != null ? inc.getDescription() : "",
                    inc.getAmount()
            ));
        }

        for (Expense exp : expenses) {
            String category = exp.getCategory() != null ? exp.getCategory().getCategoryName() : "Other";
            transactions.add(new TransactionDTO(
                    exp.getExpenseDate(),
                    "Expense",
                    category,
                    exp.getDescription() != null ? exp.getDescription() : "",
                    exp.getAmount()
            ));
        }

        // Sort descending by transaction date
        transactions.sort(Comparator.comparing(TransactionDTO::getTransactionDate, Comparator.nullsLast(Comparator.reverseOrder())));

        return transactions;
    }
}
