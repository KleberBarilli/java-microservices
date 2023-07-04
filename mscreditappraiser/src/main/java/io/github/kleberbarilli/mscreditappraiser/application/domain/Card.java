package io.github.kleberbarilli.mscreditappraiser.application.domain;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Card {
    private Long id;
    private String name;
    private String brand;
    private BigDecimal initialLimit;

}
