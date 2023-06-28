package io.github.kleberbarilli.msicards.application.infra.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.kleberbarilli.msicards.application.domain.entities.CardCustomer;

public interface CardCustomerRepository extends JpaRepository<CardCustomer, Long> {

    List<CardCustomer> findByCpf(String cpf);

}
