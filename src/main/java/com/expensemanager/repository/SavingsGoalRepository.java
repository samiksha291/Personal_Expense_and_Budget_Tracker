package com.expensemanager.repository;

import com.expensemanager.entity.SavingsGoal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SavingsGoalRepository extends JpaRepository<SavingsGoal, Integer> {
    Optional<SavingsGoal> findTopByOrderByGoalIdDesc();
}
