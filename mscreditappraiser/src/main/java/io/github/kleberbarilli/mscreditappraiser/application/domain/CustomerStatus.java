package io.github.kleberbarilli.mscreditappraiser.application.domain;

import java.util.List;

import lombok.Data;

@Data
public class CustomerStatus {
    private CustomerData customer;
    private List<CustomerCard> cards;
}
