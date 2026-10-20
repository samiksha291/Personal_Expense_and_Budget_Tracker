package com.expensemanager.service;

import com.expensemanager.dto.ExpenseRequestDTO;
import com.expensemanager.dto.ExpenseResponseDTO;
import com.expensemanager.entity.Category;
import com.expensemanager.entity.Expense;
import com.expensemanager.repository.CategoryRepository;
import com.expensemanager.repository.ExpenseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final CategoryRepository categoryRepository;

    public ExpenseService(ExpenseRepository expenseRepository, CategoryRepository categoryRepository) {
        this.expenseRepository = expenseRepository;
        this.categoryRepository = categoryRepository;
    }

    @Transactional(readOnly = true)
    public List<ExpenseResponseDTO> getAllExpenses() {
        return expenseRepository.findAllByOrderByExpenseDateDesc().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ExpenseResponseDTO> searchExpenses(Integer categoryId, LocalDate expenseDate, Double amount) {
        return expenseRepository.searchExpenses(categoryId, expenseDate, amount).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ExpenseResponseDTO getExpenseById(Integer id) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Expense not found with ID: " + id));
        return mapToDTO(expense);
    }

    public ExpenseResponseDTO createExpense(ExpenseRequestDTO dto) {
        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Category not found with ID: " + dto.getCategoryId()));

        Expense expense = new Expense(
                category,
                dto.getAmount(),
                dto.getExpenseDate(),
                dto.getDescription()
        );

        Expense saved = expenseRepository.save(expense);
        return mapToDTO(saved);
    }

    public ExpenseResponseDTO updateExpense(Integer id, ExpenseRequestDTO dto) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Expense not found with ID: " + id));

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Category not found with ID: " + dto.getCategoryId()));

        expense.setCategory(category);
        expense.setAmount(dto.getAmount());
        expense.setExpenseDate(dto.getExpenseDate());
        expense.setDescription(dto.getDescription());

        Expense updated = expenseRepository.save(expense);
        return mapToDTO(updated);
    }

    public void deleteExpense(Integer id) {
        if (!expenseRepository.existsById(id)) {
            throw new IllegalArgumentException("Expense not found with ID: " + id);
        }
        expenseRepository.deleteById(id);
    }

    private ExpenseResponseDTO mapToDTO(Expense expense) {
        return new ExpenseResponseDTO(
                expense.getExpenseId(),
                expense.getCategoryId(),
                expense.getCategoryName(),
                expense.getAmount(),
                expense.getExpenseDate(),
                expense.getDescription()
        );
    }
}
