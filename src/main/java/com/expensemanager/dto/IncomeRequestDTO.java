package com.expensemanager.dto;

import java.time.LocalDate;

public class IncomeRequestDTO {
    private String source;
    private Double amount;
    private LocalDate incomeDate;
    private String description;

    public IncomeRequestDTO() {
    }

    public IncomeRequestDTO(String source, Double amount, LocalDate incomeDate, String description) {
        this.source = source;
        this.amount = amount;
        this.incomeDate = incomeDate;
        this.description = description;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDate getIncomeDate() {
        return incomeDate;
    }

    public void setIncomeDate(LocalDate incomeDate) {
        this.incomeDate = incomeDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
