package io.github.kleberbarilli.mscreditappraiser.application.services;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import io.github.kleberbarilli.mscreditappraiser.application.domain.CustomerCard;
import io.github.kleberbarilli.mscreditappraiser.application.domain.CustomerData;
import io.github.kleberbarilli.mscreditappraiser.application.domain.CustomerStatus;
import io.github.kleberbarilli.mscreditappraiser.infra.clients.CardsResourceClient;
import io.github.kleberbarilli.mscreditappraiser.infra.clients.CustomerResourceClient;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreditAppraiserService {

    private final CustomerResourceClient customersClient;
    private final CardsResourceClient cardsClient;

    public CustomerStatus getCustomerStatus(String cpf) {
        ResponseEntity<CustomerData> customerResponse = customersClient.getCustomerData(cpf);
        ResponseEntity<List<CustomerCard>> cardsResponse = cardsClient.getCustomerICards(cpf);

        return CustomerStatus.builder()
                .customer(customerResponse.getBody())
                .cards(cardsResponse.getBody())
                .build();
    }

}
