package com.expensemanager.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;

public class TransactionDTO {
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate transactionDate;
    private String type; // "Income" or "Expense"
    private String category; // category_name for expense, source for income
    private String description;
    private Double amount;

    public TransactionDTO() {
    }

    public TransactionDTO(LocalDate transactionDate, String type, String category, String description, Double amount) {
        this.transactionDate = transactionDate;
        this.type = type;
        this.category = category;
        this.description = description;
        this.amount = amount;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
