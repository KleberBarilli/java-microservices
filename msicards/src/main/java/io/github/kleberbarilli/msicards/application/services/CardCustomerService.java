package io.github.kleberbarilli.msicards.application.services;

import java.util.List;

import org.springframework.stereotype.Service;

import io.github.kleberbarilli.msicards.application.domain.entities.CardCustomer;
import io.github.kleberbarilli.msicards.application.infra.repositories.CardCustomerRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CardCustomerService {

    private CardCustomerRepository repository;

    public List<CardCustomer> findICardsByCpf(String cpf) {
        return repository.findByCpf(cpf);
    }
}
