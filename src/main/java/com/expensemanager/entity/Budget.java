package com.expensemanager.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "budget")
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "budget_id")
    private Integer budgetId;

    @Column(name = "month_year", nullable = false, unique = true, length = 7)
    private String monthYear;

    @Column(name = "budget_amount", nullable = false)
    private Double budgetAmount;

    public Budget() {
    }

    public Budget(String monthYear, Double budgetAmount) {
        this.monthYear = monthYear;
        this.budgetAmount = budgetAmount;
    }

    public Integer getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(Integer budgetId) {
        this.budgetId = budgetId;
    }

    public String getMonthYear() {
        return monthYear;
    }

    public void setMonthYear(String monthYear) {
        this.monthYear = monthYear;
    }

    public Double getBudgetAmount() {
        return budgetAmount;
    }

    public void setBudgetAmount(Double budgetAmount) {
        this.budgetAmount = budgetAmount;
    }
}
