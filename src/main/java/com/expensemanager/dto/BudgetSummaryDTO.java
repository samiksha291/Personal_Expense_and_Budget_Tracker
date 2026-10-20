package com.expensemanager.dto;

public class BudgetSummaryDTO {
    private Integer month;
    private Integer year;
    private Double budget;
    private Double totalExpenses;
    private Double remaining;

    public BudgetSummaryDTO() {
    }

    public BudgetSummaryDTO(Integer month, Integer year, Double budget, Double totalExpenses, Double remaining) {
        this.month = month;
        this.year = year;
        this.budget = budget;
        this.totalExpenses = totalExpenses;
        this.remaining = remaining;
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

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public Double getTotalExpenses() {
        return totalExpenses;
    }

    public void setTotalExpenses(Double totalExpenses) {
        this.totalExpenses = totalExpenses;
    }

    public Double getRemaining() {
        return remaining;
    }

    public void setRemaining(Double remaining) {
        this.remaining = remaining;
    }
}
