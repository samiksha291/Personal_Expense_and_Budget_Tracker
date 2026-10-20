package com.expensemanager.repository;

import com.expensemanager.entity.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Integer> {

    List<Income> findAllByOrderByIncomeDateDesc();

    @Query("SELECT i FROM Income i WHERE " +
           "(:source IS NULL OR :source = '' OR LOWER(i.source) LIKE LOWER(CONCAT('%', :source, '%'))) AND " +
           "(:incomeDate IS NULL OR i.incomeDate = :incomeDate) AND " +
           "(:amount IS NULL OR i.amount = :amount) " +
           "ORDER BY i.incomeDate DESC")
    List<Income> searchIncome(@Param("source") String source,
                              @Param("incomeDate") LocalDate incomeDate,
                              @Param("amount") Double amount);

    @Query("SELECT COALESCE(SUM(i.amount), 0.0) FROM Income i")
    Double getTotalIncome();
}
