package io.github.kleberbarilli.mscreditappraiser.infra.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.github.kleberbarilli.mscreditappraiser.application.domain.Card;
import io.github.kleberbarilli.mscreditappraiser.application.domain.CustomerCard;

@FeignClient(value = "msicards", path = "/icards")
public interface CardsResourceClient {

    @GetMapping(params = "cpf")
    ResponseEntity<List<CustomerCard>> getCustomerICards(@RequestParam("cpf") String cpf);

    @GetMapping(params = "income")
    ResponseEntity<List<Card>> getICardsPerIncome(@RequestParam("income") Long income);
}
