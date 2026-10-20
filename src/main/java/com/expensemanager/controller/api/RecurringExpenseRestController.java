package com.expensemanager.controller.api;

import com.expensemanager.dto.RecurringExpenseRequestDTO;
import com.expensemanager.entity.RecurringExpense;
import com.expensemanager.service.RecurringExpenseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recurring-expenses")
@CrossOrigin(origins = "*")
public class RecurringExpenseRestController {

    private final RecurringExpenseService recurringExpenseService;

    public RecurringExpenseService getRecurringExpenseService() {
        return recurringExpenseService;
    }

    public RecurringExpenseRestController(RecurringExpenseService recurringExpenseService) {
        this.recurringExpenseService = recurringExpenseService;
    }

    @GetMapping
    public ResponseEntity<List<RecurringExpense>> getAllRecurringExpenses() {
        return ResponseEntity.ok(recurringExpenseService.getAllRecurringExpenses());
    }

    @PostMapping
    public ResponseEntity<RecurringExpense> createRecurringExpense(@RequestBody RecurringExpenseRequestDTO dto) {
        RecurringExpense created = recurringExpenseService.createRecurringExpense(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecurringExpense(@PathVariable Integer id) {
        recurringExpenseService.deleteRecurringExpense(id);
        return ResponseEntity.noContent().build();
    }
}
