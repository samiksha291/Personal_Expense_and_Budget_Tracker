package com.expensemanager.dto;

import java.time.LocalDate;

public class ExpenseRequestDTO {
    private Integer categoryId;
    private Double amount;
    private LocalDate expenseDate;
    private String description;

    public ExpenseRequestDTO() {
    }

    public ExpenseRequestDTO(Integer categoryId, Double amount, LocalDate expenseDate, String description) {
        this.categoryId = categoryId;
        this.amount = amount;
        this.expenseDate = expenseDate;
        this.description = description;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDate getExpenseDate() {
        return expenseDate;
    }

    public void setExpenseDate(LocalDate expenseDate) {
        this.expenseDate = expenseDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
