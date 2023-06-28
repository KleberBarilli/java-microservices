package io.github.kleberbarilli.mscustomers.application.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.google.common.base.Optional;

import io.github.kleberbarilli.mscustomers.application.domain.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByCpf(String cpf);
}
