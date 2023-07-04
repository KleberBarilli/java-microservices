package io.github.kleberbarilli.mscreditappraiser.application.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerStatus {
    private CustomerData customer;
    private List<CustomerCard> cards;
}
