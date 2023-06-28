package io.github.kleberbarilli.mscustomers.application.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import io.github.kleberbarilli.mscustomers.application.domain.entities.Customer;
import io.github.kleberbarilli.mscustomers.application.infra.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;

    @Transactional
    public Customer save(Customer customer) {
        return repository.save(customer);
    }

    public Optional<Customer> getByCpf(String cpf) {
        return repository.findByCpf(cpf);
    }

}
