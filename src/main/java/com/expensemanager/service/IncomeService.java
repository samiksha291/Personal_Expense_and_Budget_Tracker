package com.expensemanager.service;

import com.expensemanager.dto.IncomeRequestDTO;
import com.expensemanager.entity.Income;
import com.expensemanager.repository.IncomeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class IncomeService {

    private final IncomeRepository incomeRepository;

    public IncomeService(IncomeRepository incomeRepository) {
        this.incomeRepository = incomeRepository;
    }

    @Transactional(readOnly = true)
    public List<Income> getAllIncome() {
        return incomeRepository.findAllByOrderByIncomeDateDesc();
    }

    @Transactional(readOnly = true)
    public List<Income> searchIncome(String source, LocalDate incomeDate, Double amount) {
        return incomeRepository.searchIncome(source, incomeDate, amount);
    }

    @Transactional(readOnly = true)
    public Income getIncomeById(Integer id) {
        return incomeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Income not found with ID: " + id));
    }

    public Income createIncome(IncomeRequestDTO dto) {
        Income income = new Income(
                dto.getSource(),
                dto.getAmount(),
                dto.getIncomeDate(),
                dto.getDescription()
        );
        return incomeRepository.save(income);
    }

    public void deleteIncome(Integer id) {
        if (!incomeRepository.existsById(id)) {
            throw new IllegalArgumentException("Income not found with ID: " + id);
        }
        incomeRepository.deleteById(id);
    }
}
