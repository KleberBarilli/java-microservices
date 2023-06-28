package io.github.kleberbarilli.mscustomers.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.kleberbarilli.mscustomers.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
