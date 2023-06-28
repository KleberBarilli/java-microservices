package io.github.kleberbarilli.mscustomers.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cpf;

    private String name;

    @Column(name = "birth_date")
    private String birthDate;

    public Customer(String cpf, String name, String birthDate) {
        this.cpf = cpf;
        this.name = name;
        this.birthDate = birthDate;
    }

}
