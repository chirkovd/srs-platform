package org.systems.dipe.srs.orchestration.events.queue;

import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import org.systems.dipe.srs.orchestration.config.SrsExchangeConfig;
import org.systems.dipe.srs.orchestration.events.EventMessage;
import org.systems.dipe.srs.orchestration.events.EventQueue;

@Component
@AllArgsConstructor
public class EventsPublisher implements EventQueue {

    private final RabbitTemplate rabbitTemplate;

    @Override
    public <T extends EventMessage> void pushMessage(T message) {
        rabbitTemplate.convertAndSend(
                SrsExchangeConfig.SRS_EXCHANGE,
                SrsExchangeConfig.SRS_CAMUNDA_ROUTING_KEY,
                message
        );
    }
}
