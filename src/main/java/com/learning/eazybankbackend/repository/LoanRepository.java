package com.learning.eazybankbackend.repository;

import com.learning.eazybankbackend.model.Loans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;
import java.util.Optional;

public interface LoanRepository extends JpaRepository<Loans, Long> {
    @PreAuthorize("hasRole('ROOT')")
    Optional<List<Loans>> findByCustomerIdOrderByStartDateDesc(Long customerId);
}
