package com.learning.eazybankbackend.controller;


import com.learning.eazybankbackend.model.Accounts;
import com.learning.eazybankbackend.repository.AccountRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class AccountController {

    private final AccountRepository accountRepository;

    public AccountController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @GetMapping("/myAccount")
    public Accounts getAccountDetails(@RequestParam Long id){
        Optional<Accounts> accounts = accountRepository.findByCustomerId(id);
        return accounts.orElse(null);
    }
}
