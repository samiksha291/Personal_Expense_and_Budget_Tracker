package com.expensemanager.service;

import com.expensemanager.dto.RecurringExpenseRequestDTO;
import com.expensemanager.entity.Category;
import com.expensemanager.entity.RecurringExpense;
import com.expensemanager.repository.CategoryRepository;
import com.expensemanager.repository.RecurringExpenseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RecurringExpenseService {

    private final RecurringExpenseRepository recurringExpenseRepository;
    private final CategoryRepository categoryRepository;

    public RecurringExpenseService(RecurringExpenseRepository recurringExpenseRepository,
                                   CategoryRepository categoryRepository) {
        this.recurringExpenseRepository = recurringExpenseRepository;
        this.categoryRepository = categoryRepository;
    }

    @Transactional(readOnly = true)
    public List<RecurringExpense> getAllRecurringExpenses() {
        return recurringExpenseRepository.findAllByOrderByNextDueDateAsc();
    }

    public RecurringExpense createRecurringExpense(RecurringExpenseRequestDTO dto) {
        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Category not found with ID: " + dto.getCategoryId()));

        RecurringExpense recurringExpense = new RecurringExpense(
                dto.getExpenseName(),
                dto.getAmount(),
                category,
                dto.getFrequency(),
                dto.getNextDueDate(),
                dto.getDescription()
        );

        return recurringExpenseRepository.save(recurringExpense);
    }

    public void deleteRecurringExpense(Integer id) {
        if (!recurringExpenseRepository.existsById(id)) {
            throw new IllegalArgumentException("Recurring expense not found with ID: " + id);
        }
        recurringExpenseRepository.deleteById(id);
    }
}
