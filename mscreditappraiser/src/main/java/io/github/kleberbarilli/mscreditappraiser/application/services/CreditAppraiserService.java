package io.github.kleberbarilli.mscreditappraiser.application.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import feign.FeignException.FeignClientException;
import io.github.kleberbarilli.mscreditappraiser.application.domain.AppraiserResponse;
import io.github.kleberbarilli.mscreditappraiser.application.domain.ApprovedCard;
import io.github.kleberbarilli.mscreditappraiser.application.domain.Card;
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

    public AppraiserResponse creditAnalysis(String cpf, Long income) throws NotFoundException, MsException {
        try {
            ResponseEntity<List<Card>> cardsResponse = cardsClient.getICardsPerIncome(income);

            List<Card> cards = cardsResponse.getBody();
            var approvedCards = cards.stream().map(card -> {

                BigDecimal limit = card.getInitialLimit();
                BigDecimal incomeBD = BigDecimal.valueOf(income);

                double randomDeviation = -0.1 + new Random().nextDouble() * 0.2;
                BigDecimal deviationAmount = limit.multiply(BigDecimal.valueOf(randomDeviation));

                ApprovedCard approvedCard = new ApprovedCard();
                approvedCard.setCard(card.getName());
                approvedCard.setBrand(card.getBrand());
                approvedCard.setLimit(incomeBD.add(deviationAmount));

                return approvedCard;
            }).collect(Collectors.toList());

            return new AppraiserResponse(approvedCards);

        } catch (FeignClientException e) {
            int status = e.status();

            if (HttpStatus.NOT_FOUND.value() == status) {
                throw new NotFoundException("Customer");
            }
            throw new MsException(e.getMessage(), e.status());
        }
    }

}
