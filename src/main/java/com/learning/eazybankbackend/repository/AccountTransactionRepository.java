package com.learning.eazybankbackend.repository;

import com.learning.eazybankbackend.model.AccountsTransactions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountTransactionRepository extends JpaRepository<AccountsTransactions, Long> {
    Optional<List<AccountsTransactions>> findByCustomerIdOrderByTransactionDateDesc(Long customerId);
}
