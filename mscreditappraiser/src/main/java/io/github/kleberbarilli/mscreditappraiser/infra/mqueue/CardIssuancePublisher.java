package io.github.kleberbarilli.mscreditappraiser.infra.mqueue;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.kleberbarilli.mscreditappraiser.application.domain.CardIssuanceRequest;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CardIssuancePublisher {

    private final RabbitTemplate rabbitTemplate;
    private final Queue queue;

    public void cardIssuance(CardIssuanceRequest data) throws JsonProcessingException {
        var json = convertIntoJson(data);
        rabbitTemplate.convertAndSend(queue.getName(), json);
    }

    public String convertIntoJson(CardIssuanceRequest data) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        var json = mapper.writeValueAsString(data);

        return json;
    }
}
