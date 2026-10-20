package com.expensemanager.dto;

import java.time.LocalDate;

public class SavingsGoalRequestDTO {
    private Integer goalId;
    private String goalName;
    private Double targetAmount;
    private Double currentAmount;
    private LocalDate targetDate;

    public SavingsGoalRequestDTO() {
    }

    public SavingsGoalRequestDTO(Integer goalId, String goalName, Double targetAmount, Double currentAmount, LocalDate targetDate) {
        this.goalId = goalId;
        this.goalName = goalName;
        this.targetAmount = targetAmount;
        this.currentAmount = currentAmount;
        this.targetDate = targetDate;
    }

    public Integer getGoalId() {
        return goalId;
    }

    public void setGoalId(Integer goalId) {
        this.goalId = goalId;
    }

    public String getGoalName() {
        return goalName;
    }

    public void setGoalName(String goalName) {
        this.goalName = goalName;
    }

    public Double getTargetAmount() {
        return targetAmount;
    }

    public void setTargetAmount(Double targetAmount) {
        this.targetAmount = targetAmount;
    }

    public Double getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(Double currentAmount) {
        this.currentAmount = currentAmount;
    }

    public LocalDate getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(LocalDate targetDate) {
        this.targetDate = targetDate;
    }
}
