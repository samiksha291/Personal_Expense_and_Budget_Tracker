package com.expensemanager.dto;

public class MonthlySummaryDTO {
    private Double totalIncome;
    private Double totalExpenses;
    private String highestCategory;
    private Double remaining;

    public MonthlySummaryDTO() {
    }

    public MonthlySummaryDTO(Double totalIncome, Double totalExpenses, String highestCategory, Double remaining) {
        this.totalIncome = totalIncome;
        this.totalExpenses = totalExpenses;
        this.highestCategory = highestCategory != null ? highestCategory : "None";
        this.remaining = remaining;
    }

    public Double getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(Double totalIncome) {
        this.totalIncome = totalIncome;
    }

    public Double getTotalExpenses() {
        return totalExpenses;
    }

    public void setTotalExpenses(Double totalExpenses) {
        this.totalExpenses = totalExpenses;
    }

    public String getHighestCategory() {
        return highestCategory;
    }

    public void setHighestCategory(String highestCategory) {
        this.highestCategory = highestCategory;
    }

    public Double getRemaining() {
        return remaining;
    }

    public void setRemaining(Double remaining) {
        this.remaining = remaining;
    }
}
