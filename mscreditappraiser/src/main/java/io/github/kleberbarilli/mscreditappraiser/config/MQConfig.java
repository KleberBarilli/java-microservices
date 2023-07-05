package io.github.kleberbarilli.mscreditappraiser.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {

    @Value("${mq.queues.card-issuance}")
    private String queueCardIssuance;

    @Bean
    public Queue queueCardIssuance() {
        return new Queue(queueCardIssuance, true);
    }
}
