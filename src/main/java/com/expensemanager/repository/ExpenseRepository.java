package com.expensemanager.repository;

import com.expensemanager.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Integer> {

    List<Expense> findAllByOrderByExpenseDateDesc();

    @Query("SELECT e FROM Expense e WHERE " +
           "(:categoryId IS NULL OR e.category.categoryId = :categoryId) AND " +
           "(:expenseDate IS NULL OR e.expenseDate = :expenseDate) AND " +
           "(:amount IS NULL OR e.amount = :amount) " +
           "ORDER BY e.expenseDate DESC")
    List<Expense> searchExpenses(@Param("categoryId") Integer categoryId,
                                 @Param("expenseDate") LocalDate expenseDate,
                                 @Param("amount") Double amount);

    @Query("SELECT COALESCE(SUM(e.amount), 0.0) FROM Expense e WHERE " +
           "FUNCTION('MONTH', e.expenseDate) = :month AND " +
           "FUNCTION('YEAR', e.expenseDate) = :year")
    Double getMonthlyExpenseTotal(@Param("month") int month, @Param("year") int year);

    @Query("SELECT COALESCE(SUM(e.amount), 0.0) FROM Expense e")
    Double getTotalExpenses();

    @Query(value = "SELECT c.category_name " +
                   "FROM expenses e " +
                   "JOIN categories c ON e.category_id = c.category_id " +
                   "GROUP BY c.category_id, c.category_name " +
                   "ORDER BY SUM(e.amount) DESC " +
                   "LIMIT 1", nativeQuery = true)
    String getHighestExpenseCategory();
}
