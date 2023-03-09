package com.learning.eazybankbackend.repository;


import com.learning.eazybankbackend.model.Cards;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CardsRepository extends JpaRepository<Cards, Long> {
    Optional<List<Cards>> findByCustomerId(Long customerId);
}
