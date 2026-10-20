package com.expensemanager.controller.api;

import com.expensemanager.dto.IncomeRequestDTO;
import com.expensemanager.entity.Income;
import com.expensemanager.service.IncomeService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/income")
@CrossOrigin(origins = "*")
public class IncomeRestController {

    private final IncomeService incomeService;

    public IncomeRestController(IncomeService incomeService) {
        this.incomeService = incomeService;
    }

    @GetMapping
    public ResponseEntity<List<Income>> getIncome(
            @RequestParam(required = false) String source,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate incomeDate,
            @RequestParam(required = false) Double amount) {

        if (source != null || incomeDate != null || amount != null) {
            return ResponseEntity.ok(incomeService.searchIncome(source, incomeDate, amount));
        }
        return ResponseEntity.ok(incomeService.getAllIncome());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Income> getIncomeById(@PathVariable Integer id) {
        return ResponseEntity.ok(incomeService.getIncomeById(id));
    }

    @PostMapping
    public ResponseEntity<Income> createIncome(@RequestBody IncomeRequestDTO dto) {
        Income created = incomeService.createIncome(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIncome(@PathVariable Integer id) {
        incomeService.deleteIncome(id);
        return ResponseEntity.noContent().build();
    }
}
