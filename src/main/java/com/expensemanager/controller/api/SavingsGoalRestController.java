package com.expensemanager.controller.api;

import com.expensemanager.dto.SavingsGoalRequestDTO;
import com.expensemanager.dto.SavingsGoalResponseDTO;
import com.expensemanager.service.SavingsGoalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/savings-goals")
@CrossOrigin(origins = "*")
public class SavingsGoalRestController {

    private final SavingsGoalService savingsGoalService;

    public SavingsGoalRestController(SavingsGoalService savingsGoalService) {
        this.savingsGoalService = savingsGoalService;
    }

    @GetMapping("/latest")
    public ResponseEntity<SavingsGoalResponseDTO> getLatestSavingsGoal() {
        SavingsGoalResponseDTO goal = savingsGoalService.getLatestSavingsGoal();
        if (goal == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(goal);
    }

    @PostMapping
    public ResponseEntity<SavingsGoalResponseDTO> saveOrUpdateGoal(@RequestBody SavingsGoalRequestDTO dto) {
        SavingsGoalResponseDTO saved = savingsGoalService.saveOrUpdateSavingsGoal(dto);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SavingsGoalResponseDTO> updateGoal(@PathVariable Integer id,
                                                             @RequestBody SavingsGoalRequestDTO dto) {
        dto.setGoalId(id);
        SavingsGoalResponseDTO updated = savingsGoalService.saveOrUpdateSavingsGoal(dto);
        return ResponseEntity.ok(updated);
    }
}
