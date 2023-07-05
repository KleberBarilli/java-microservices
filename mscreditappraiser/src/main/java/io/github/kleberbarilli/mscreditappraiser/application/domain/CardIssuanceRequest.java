package io.github.kleberbarilli.mscreditappraiser.application.domain;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class CardIssuanceRequest {

    private Long cardId;
    private String cpf;
    private String address;
    private BigDecimal cardLimit;
}
