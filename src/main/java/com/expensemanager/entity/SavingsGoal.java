package com.expensemanager.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "savings_goal")
public class SavingsGoal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "goal_id")
    private Integer goalId;

    @Column(name = "goal_name", nullable = false, length = 100)
    private String goalName;

    @Column(name = "target_amount", nullable = false)
    private Double targetAmount;

    @Column(name = "current_amount")
    private Double currentAmount = 0.0;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "target_date")
    private LocalDate targetDate;

    public SavingsGoal() {
    }

    public SavingsGoal(String goalName, Double targetAmount, Double currentAmount, LocalDate targetDate) {
        this.goalName = goalName;
        this.targetAmount = targetAmount;
        this.currentAmount = currentAmount != null ? currentAmount : 0.0;
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
        this.currentAmount = currentAmount != null ? currentAmount : 0.0;
    }

    public LocalDate getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(LocalDate targetDate) {
        this.targetDate = targetDate;
    }

    @Transient
    public Double getRemaining() {
        if (targetAmount == null) return 0.0;
        double current = currentAmount != null ? currentAmount : 0.0;
        return Math.max(0.0, targetAmount - current);
    }

    @Transient
    public Double getProgress() {
        if (targetAmount == null || targetAmount <= 0) return 0.0;
        double current = currentAmount != null ? currentAmount : 0.0;
        double progress = (current / targetAmount) * 100.0;
        return Math.min(100.0, Math.max(0.0, progress));
    }
}
