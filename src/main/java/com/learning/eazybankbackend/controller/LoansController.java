package com.learning.eazybankbackend.controller;

import com.learning.eazybankbackend.model.Loans;
import com.learning.eazybankbackend.repository.LoanRepository;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class LoansController {

    private final LoanRepository loanRepository;

    public LoansController(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    @GetMapping("/myLoans")
    @PostAuthorize("hasRole('USER')")
    public List<Loans> getLoanDetails(@RequestParam Long id){
        Optional<List<Loans>> loans = loanRepository.findByCustomerIdOrderByStartDateDesc(id);
        return loans.orElse(null);
    }
}
