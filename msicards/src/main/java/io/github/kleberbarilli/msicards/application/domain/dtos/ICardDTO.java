package io.github.kleberbarilli.msicards.application.domain.dtos;

import java.math.BigDecimal;

import io.github.kleberbarilli.msicards.application.domain.entities.ICard;
import io.github.kleberbarilli.msicards.application.domain.enums.ICardBrand;
import lombok.Data;

@Data
public class ICardDTO {

    private String name;
    private ICardBrand cardBand;
    private BigDecimal income;
    private BigDecimal initialLimit;

    public ICard toEntity() {
        return new ICard(name, cardBand, income, initialLimit);
    }
}
