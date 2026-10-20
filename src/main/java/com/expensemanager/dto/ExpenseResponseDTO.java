package com.expensemanager.dto;

import java.time.LocalDate;

public class ExpenseResponseDTO {
    private Integer expenseId;
    private Integer categoryId;
    private String categoryName;
    private Double amount;
    private LocalDate expenseDate;
    private String description;

    public ExpenseResponseDTO() {
    }

    public ExpenseResponseDTO(Integer expenseId, Integer categoryId, String categoryName, Double amount, LocalDate expenseDate, String description) {
        this.expenseId = expenseId;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.amount = amount;
        this.expenseDate = expenseDate;
        this.description = description;
    }

    public Integer getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(Integer expenseId) {
        this.expenseId = expenseId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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
