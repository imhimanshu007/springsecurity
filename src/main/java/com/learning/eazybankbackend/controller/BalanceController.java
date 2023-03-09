package com.learning.eazybankbackend.controller;


import com.learning.eazybankbackend.model.AccountsTransactions;
import com.learning.eazybankbackend.repository.AccountTransactionRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class BalanceController {

    private AccountTransactionRepository accountTransactionRepository;

    public BalanceController(AccountTransactionRepository accountTransactionRepository) {
        this.accountTransactionRepository = accountTransactionRepository;
    }
    @GetMapping("myBalance")
    public List<AccountsTransactions> getBalanceDetails(@RequestParam Long id){
        Optional<List<AccountsTransactions>> accountsTransactions = accountTransactionRepository
                .findByCustomerIdOrderByTransactionDateDesc(id);

        return accountsTransactions.orElse(null);
    }
}
