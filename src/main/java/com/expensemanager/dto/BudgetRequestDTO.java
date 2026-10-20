package com.expensemanager.dto;

public class BudgetRequestDTO {
    private Integer month;
    private Integer year;
    private Double amount;

    public BudgetRequestDTO() {
    }

    public BudgetRequestDTO(Integer month, Integer year, Double amount) {
        this.month = month;
        this.year = year;
        this.amount = amount;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
