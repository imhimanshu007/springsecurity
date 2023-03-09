package com.learning.eazybankbackend.controller;

import com.learning.eazybankbackend.model.Cards;
import com.learning.eazybankbackend.repository.CardsRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class CardsController {

    private final CardsRepository cardsRepository;

    public CardsController(CardsRepository cardsRepository) {
        this.cardsRepository = cardsRepository;
    }

    @GetMapping("/myCards")
    public List<Cards> getCardDetails(@RequestParam Long id){
        Optional<List<Cards>> cards = cardsRepository.findByCustomerId(id);
        return cards.orElse(null);
    }
}
