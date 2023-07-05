package io.github.kleberbarilli.msicards.application.infra.mqueue;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.kleberbarilli.msicards.application.domain.entities.CardCustomer;
import io.github.kleberbarilli.msicards.application.domain.entities.CardIssuanceRequest;
import io.github.kleberbarilli.msicards.application.domain.entities.ICard;
import io.github.kleberbarilli.msicards.application.infra.repositories.CardCustomerRepository;
import io.github.kleberbarilli.msicards.application.infra.repositories.ICardRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CardIssuanceSubscriber {

    private final ICardRepository iCardRepository;
    private final CardCustomerRepository cardCustomerRepository;

    @RabbitListener(queues = "${mq.queues.card-issuance}")
    public void receiveIssuance(@Payload String payload) {

        try {
            var mapper = new ObjectMapper();

            CardIssuanceRequest data = mapper.readValue(payload, CardIssuanceRequest.class);

            ICard iCard = iCardRepository.findById(data.getCardId()).orElseThrow();

            CardCustomer cardCustomer = new CardCustomer();

            cardCustomer.setCard(iCard);
            cardCustomer.setCpf(data.getCpf());
            cardCustomer.setLimit(data.getCardLimit());

            cardCustomerRepository.save(cardCustomer);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }
}
