package com.expensemanager.repository;

import com.expensemanager.entity.RecurringExpense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecurringExpenseRepository extends JpaRepository<RecurringExpense, Integer> {
    List<RecurringExpense> findAllByOrderByNextDueDateAsc();
}
