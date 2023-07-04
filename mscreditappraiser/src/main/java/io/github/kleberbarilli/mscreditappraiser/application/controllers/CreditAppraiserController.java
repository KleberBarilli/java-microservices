package io.github.kleberbarilli.mscreditappraiser.application.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.kleberbarilli.mscreditappraiser.application.domain.CustomerStatus;
import io.github.kleberbarilli.mscreditappraiser.application.services.CreditAppraiserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("credit-appraiser")
@RequiredArgsConstructor
public class CreditAppraiserController {

    private final CreditAppraiserService creditAppraiserService;

    @GetMapping
    public String healthCheck() {
        return "ok";
    }

    @GetMapping(value = "customer-status", params = "cpf")
    public ResponseEntity<CustomerStatus> getCustomerStatus(@RequestParam("cpf") String cpf) {
        CustomerStatus customerStatus = creditAppraiserService.getCustomerStatus(cpf);

        return ResponseEntity.ok(customerStatus);
    }
}