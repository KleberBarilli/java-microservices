package io.github.kleberbarilli.msicards.application.infra.mqueue;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class CardIssuance {

    @RabbitListener(queues = "${mq.queues.card-issuance}")
    public void receiveIssuance(@Payload String payload) {
        System.out.println(payload);
    }
}
