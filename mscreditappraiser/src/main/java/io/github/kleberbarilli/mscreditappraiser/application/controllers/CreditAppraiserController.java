package io.github.kleberbarilli.mscreditappraiser.application.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.kleberbarilli.mscreditappraiser.application.domain.AppraiserData;
import io.github.kleberbarilli.mscreditappraiser.application.domain.AppraiserResponse;
import io.github.kleberbarilli.mscreditappraiser.application.domain.CustomerStatus;
import io.github.kleberbarilli.mscreditappraiser.application.exceptions.MsException;
import io.github.kleberbarilli.mscreditappraiser.application.exceptions.NotFoundException;
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
    public ResponseEntity getCustomerStatus(@RequestParam("cpf") String cpf) {

        try {
            CustomerStatus customerStatus = creditAppraiserService.getCustomerStatus(cpf);
            return ResponseEntity.ok(customerStatus);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (MsException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }

    }

    @PostMapping
    public ResponseEntity creditAppraiser(@RequestBody AppraiserData data) {

        try {
            AppraiserResponse creditReview = creditAppraiserService.creditAnalysis(data.getCpf(), data.getIncome());

            return ResponseEntity.ok(creditReview);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (MsException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }
    }
}
