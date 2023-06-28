package io.github.kleberbarilli.mscustomers.application.domain.dtos;

import io.github.kleberbarilli.mscustomers.application.domain.entities.Customer;
import lombok.Data;

@Data
public class CreateCustomerDTO {
    private String cpf;
    private String name;
    private String birthDate;

    public Customer toEntity() {
        return new Customer(cpf, name, birthDate);
    }
}
