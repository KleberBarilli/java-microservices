package io.github.kleberbarilli.mscustomers.application.controller;

import java.net.URI;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.github.kleberbarilli.mscustomers.application.domain.dtos.CreateCustomerDTO;
import io.github.kleberbarilli.mscustomers.application.domain.entities.Customer;
import io.github.kleberbarilli.mscustomers.application.services.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("customers")
@RequiredArgsConstructor
@Slf4j
public class CustomerController {

    private final CustomerService service;

    @GetMapping
    public String healthCheck() {
        log.info("Checking mscustomers status");
        return "ok";
    }

    @PostMapping
    public ResponseEntity save(@RequestBody CreateCustomerDTO createCustomerDTO) {
        Customer customer = createCustomerDTO.toEntity();

        service.save(customer);

        URI headerLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .query("cpf={cpf}")
                .buildAndExpand(customer.getCpf())
                .toUri();
        return ResponseEntity.created(headerLocation).build();
    }

    @GetMapping(params = "cpf")
    public ResponseEntity findCustomerByCpf(@RequestParam("cpf") String cpf) {

        Optional<Customer> customer = service.getByCpf(cpf);
        if (customer.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customer);
    }

}
