package io.github.kleberbarilli.msicards.application.domain.entities;

import java.math.BigDecimal;

import io.github.kleberbarilli.msicards.application.domain.enums.ICardBrand;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class ICard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "card_brand")
    private ICardBrand cardBand;

    private BigDecimal income;

    @Column(name = "initial_limit")
    private BigDecimal initialLimit;

    public ICard(String name, ICardBrand cardBand, BigDecimal income, BigDecimal initialLimit) {
        this.name = name;
        this.cardBand = cardBand;
        this.income = income;
        this.initialLimit = initialLimit;
    }
}
