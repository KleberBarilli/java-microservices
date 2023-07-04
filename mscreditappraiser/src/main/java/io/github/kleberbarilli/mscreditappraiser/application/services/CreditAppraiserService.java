package io.github.kleberbarilli.mscreditappraiser.application.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import feign.FeignException.FeignClientException;
import io.github.kleberbarilli.mscreditappraiser.application.domain.CustomerCard;
import io.github.kleberbarilli.mscreditappraiser.application.domain.CustomerData;
import io.github.kleberbarilli.mscreditappraiser.application.domain.CustomerStatus;
import io.github.kleberbarilli.mscreditappraiser.application.exceptions.MsException;
import io.github.kleberbarilli.mscreditappraiser.application.exceptions.NotFoundException;
import io.github.kleberbarilli.mscreditappraiser.infra.clients.CardsResourceClient;
import io.github.kleberbarilli.mscreditappraiser.infra.clients.CustomerResourceClient;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreditAppraiserService {

    private final CustomerResourceClient customersClient;
    private final CardsResourceClient cardsClient;

    public CustomerStatus getCustomerStatus(String cpf) throws NotFoundException, MsException {

        try {
            ResponseEntity<CustomerData> customerResponse = customersClient.getCustomerData(cpf);
            ResponseEntity<List<CustomerCard>> cardsResponse = cardsClient.getCustomerICards(cpf);

            return CustomerStatus.builder()
                    .customer(customerResponse.getBody())
                    .cards(cardsResponse.getBody())
                    .build();

        } catch (FeignClientException e) {
            int status = e.status();

            if (HttpStatus.NOT_FOUND.value() == status) {
                throw new NotFoundException("Customer");
            }
            throw new MsException(e.getMessage(), e.status());
        }

    }

}
