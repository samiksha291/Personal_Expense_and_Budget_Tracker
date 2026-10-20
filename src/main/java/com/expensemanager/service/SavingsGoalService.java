package com.expensemanager.service;

import com.expensemanager.dto.SavingsGoalRequestDTO;
import com.expensemanager.dto.SavingsGoalResponseDTO;
import com.expensemanager.entity.SavingsGoal;
import com.expensemanager.repository.SavingsGoalRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class SavingsGoalService {

    private final SavingsGoalRepository savingsGoalRepository;

    public SavingsGoalService(SavingsGoalRepository savingsGoalRepository) {
        this.savingsGoalRepository = savingsGoalRepository;
    }

    @Transactional(readOnly = true)
    public SavingsGoalResponseDTO getLatestSavingsGoal() {
        Optional<SavingsGoal> goalOpt = savingsGoalRepository.findTopByOrderByGoalIdDesc();
        return goalOpt.map(this::mapToDTO).orElse(null);
    }

    public SavingsGoalResponseDTO saveOrUpdateSavingsGoal(SavingsGoalRequestDTO dto) {
        SavingsGoal goal;
        if (dto.getGoalId() != null) {
            goal = savingsGoalRepository.findById(dto.getGoalId())
                    .orElse(new SavingsGoal());
        } else {
            goal = new SavingsGoal();
        }

        if (dto.getGoalName() != null && !dto.getGoalName().isBlank()) {
            goal.setGoalName(dto.getGoalName());
        }
        goal.setTargetAmount(dto.getTargetAmount());
        goal.setCurrentAmount(dto.getCurrentAmount() != null ? dto.getCurrentAmount() : 0.0);
        if (dto.getTargetDate() != null) {
            goal.setTargetDate(dto.getTargetDate());
        }

        SavingsGoal saved = savingsGoalRepository.save(goal);
        return mapToDTO(saved);
    }

    private SavingsGoalResponseDTO mapToDTO(SavingsGoal goal) {
        return new SavingsGoalResponseDTO(
                goal.getGoalId(),
                goal.getGoalName(),
                goal.getTargetAmount(),
                goal.getCurrentAmount(),
                goal.getTargetDate(),
                goal.getRemaining(),
                goal.getProgress()
        );
    }
}
