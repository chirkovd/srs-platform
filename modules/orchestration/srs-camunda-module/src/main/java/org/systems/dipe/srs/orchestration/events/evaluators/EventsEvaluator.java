package org.systems.dipe.srs.orchestration.events.evaluators;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.systems.dipe.srs.orchestration.events.Event;
import org.systems.dipe.srs.orchestration.events.EventMessage;

@Component
@AllArgsConstructor
public class EventsEvaluator {

    private final FlowRunnerEvaluator flowRunnerEvaluator;
    private final FlowNotificationEvaluator flowNotificationEvaluator;

    public void evaluate(Event event) {
        EventMessage message = event.getMessage();
        if (message.isRunner()) {
            flowRunnerEvaluator.runFlow(event);
        } else {
            flowNotificationEvaluator.notifyFlow(event);
        }
    }

}
