package org.systems.dipe.srs.orchestration.events.queue;

import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.systems.dipe.srs.orchestration.events.Event;
import org.systems.dipe.srs.orchestration.events.EventMessage;
import org.systems.dipe.srs.orchestration.events.EventsProcessor;

import static org.systems.dipe.srs.orchestration.config.SrsExchangeConfig.SRS_CAMUNDA_QUEUE;

@Component
@AllArgsConstructor
public class EventsReceiver {

    private final EventsProcessor eventsProcessor;

    @RabbitListener(queues = SRS_CAMUNDA_QUEUE)
    public void receive(EventMessage message) {
        eventsProcessor.processEvent(new Event<>(message));
    }

}
