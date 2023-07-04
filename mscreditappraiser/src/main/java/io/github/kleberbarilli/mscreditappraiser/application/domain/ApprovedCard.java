package io.github.kleberbarilli.mscreditappraiser.application.domain;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ApprovedCard {
    private String card;
    private String brand;
    private BigDecimal limit;
}
