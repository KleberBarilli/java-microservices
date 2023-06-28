package io.github.kleberbarilli.msicards.application.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.kleberbarilli.msicards.application.domain.dtos.ICardDTO;
import io.github.kleberbarilli.msicards.application.domain.entities.ICard;
import io.github.kleberbarilli.msicards.application.services.ICardService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("icards")
@RequiredArgsConstructor
public class ICardsController {

    private final ICardService service;

    @GetMapping
    public String healthCheck() {
        return "ok";
    }

    @PostMapping
    public ResponseEntity createCard(@RequestBody ICardDTO iCardDTO) {

        ICard card = iCardDTO.toEntity();
        service.save(card);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @GetMapping(params = "income")
    public ResponseEntity<List<ICard>> getICardsPerIncome(@RequestParam("income") Long income) {

        List<ICard> cardList = service.getCardsFromIncome(income);

        return ResponseEntity.ok(cardList);

    }

}
