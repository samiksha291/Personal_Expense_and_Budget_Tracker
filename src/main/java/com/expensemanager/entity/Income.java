package com.expensemanager.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "income")
public class Income {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "income_id")
    private Integer incomeId;

    @Column(name = "source", nullable = false, length = 100)
    private String source;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "income_date", nullable = false)
    private LocalDate incomeDate;

    @Column(name = "description", length = 255)
    private String description;

    public Income() {
    }

    public Income(String source, Double amount, LocalDate incomeDate, String description) {
        this.source = source;
        this.amount = amount;
        this.incomeDate = incomeDate;
        this.description = description;
    }

    public Integer getIncomeId() {
        return incomeId;
    }

    public void setIncomeId(Integer incomeId) {
        this.incomeId = incomeId;
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
