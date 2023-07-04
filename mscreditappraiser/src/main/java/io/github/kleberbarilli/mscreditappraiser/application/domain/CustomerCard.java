package io.github.kleberbarilli.mscreditappraiser.application.domain;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class CustomerCard {
    private String name;
    private String brand;
    private BigDecimal limit;
}
