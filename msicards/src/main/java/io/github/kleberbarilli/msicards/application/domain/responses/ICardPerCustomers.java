package io.github.kleberbarilli.msicards.application.domain.responses;

import java.math.BigDecimal;

import io.github.kleberbarilli.msicards.application.domain.entities.CardCustomer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ICardPerCustomers {
    private String name;
    private String brand;
    private BigDecimal limit;

    public static ICardPerCustomers fromModel(CardCustomer cardCustomer) {
        return new ICardPerCustomers(
                cardCustomer.getCard().getName(),
                cardCustomer.getCard().getCardBand().toString(),
                cardCustomer.getCard().getInitialLimit());
    }

}
