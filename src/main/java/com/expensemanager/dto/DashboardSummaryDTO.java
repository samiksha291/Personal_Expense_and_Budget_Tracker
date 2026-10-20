package com.expensemanager.dto;

public class DashboardSummaryDTO {
    private Double totalIncome;
    private Double totalExpenses;
    private Double remaining;

    public DashboardSummaryDTO() {
    }

    public DashboardSummaryDTO(Double totalIncome, Double totalExpenses, Double remaining) {
        this.totalIncome = totalIncome;
        this.totalExpenses = totalExpenses;
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

    public Double getRemaining() {
        return remaining;
    }

    public void setRemaining(Double remaining) {
        this.remaining = remaining;
    }
}
