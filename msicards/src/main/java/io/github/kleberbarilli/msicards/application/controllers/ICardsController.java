package io.github.kleberbarilli.msicards.application.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.kleberbarilli.msicards.application.domain.dtos.ICardDTO;
import io.github.kleberbarilli.msicards.application.domain.entities.CardCustomer;
import io.github.kleberbarilli.msicards.application.domain.entities.ICard;
import io.github.kleberbarilli.msicards.application.domain.responses.ICardPerCustomers;
import io.github.kleberbarilli.msicards.application.services.CardCustomerService;
import io.github.kleberbarilli.msicards.application.services.ICardService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("icards")
@RequiredArgsConstructor
public class ICardsController {

    private final ICardService iCardService;
    private final CardCustomerService cardCustomerService;

    @GetMapping
    public String healthCheck() {
        return "ok";
    }

    @PostMapping
    public ResponseEntity createCard(@RequestBody ICardDTO iCardDTO) {

        ICard card = iCardDTO.toEntity();
        iCardService.save(card);
        return ResponseEntity.ok(card);

    }

    @GetMapping(params = "income")
    public ResponseEntity<List<ICard>> getICardsPerIncome(@RequestParam("income") Long income) {

        List<ICard> cardList = iCardService.getCardsFromIncome(income);

        return ResponseEntity.ok(cardList);

    }

    @GetMapping(params = "cpf")
    public ResponseEntity<List<ICardPerCustomers>> getCustomerICards(@RequestParam("cpf") String cpf) {
        List<CardCustomer> cards = cardCustomerService.findICardsByCpf(cpf);
        List<ICardPerCustomers> resultCards = cards.stream()
                .map(ICardPerCustomers::fromModel)
                .collect(Collectors.toList());

        return ResponseEntity.ok(resultCards);

    }

}
