package com.expensemanager.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "recurring_expenses")
public class RecurringExpense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recurring_id")
    private Integer recurringId;

    @Column(name = "expense_name", nullable = false, length = 100)
    private String expenseName;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(name = "frequency", nullable = false, length = 20)
    private String frequency;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "next_due_date", nullable = false)
    private LocalDate nextDueDate;

    @Column(name = "description", length = 255)
    private String description;

    public RecurringExpense() {
    }

    public RecurringExpense(String expenseName, Double amount, Category category, String frequency, LocalDate nextDueDate, String description) {
        this.expenseName = expenseName;
        this.amount = amount;
        this.category = category;
        this.frequency = frequency;
        this.nextDueDate = nextDueDate;
        this.description = description;
    }

    public Integer getRecurringId() {
        return recurringId;
    }

    public void setRecurringId(Integer recurringId) {
        this.recurringId = recurringId;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getCategoryId() {
        return category != null ? category.getCategoryId() : null;
    }

    public String getCategoryName() {
        return category != null ? category.getCategoryName() : null;
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
