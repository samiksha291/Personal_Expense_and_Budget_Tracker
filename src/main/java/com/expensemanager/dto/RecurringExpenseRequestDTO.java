package com.expensemanager.dto;

import java.time.LocalDate;

public class RecurringExpenseRequestDTO {
    private String expenseName;
    private Double amount;
    private Integer categoryId;
    private String frequency;
    private LocalDate nextDueDate;
    private String description;

    public RecurringExpenseRequestDTO() {
    }

    public RecurringExpenseRequestDTO(String expenseName, Double amount, Integer categoryId, String frequency, LocalDate nextDueDate, String description) {
        this.expenseName = expenseName;
        this.amount = amount;
        this.categoryId = categoryId;
        this.frequency = frequency;
        this.nextDueDate = nextDueDate;
        this.description = description;
    }

    public String getExpenseName() {
        return expenseName;
    }

    public void setExpenseName(String expenseName) {
        this.expenseName = expenseName;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public LocalDate getNextDueDate() {
        return nextDueDate;
    }

    public void setNextDueDate(LocalDate nextDueDate) {
        this.nextDueDate = nextDueDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
