package org.systems.dipe.srs.orchestration.events.evaluators;

import lombok.AllArgsConstructor;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.stereotype.Component;
import org.systems.dipe.srs.orchestration.events.Event;
import org.systems.dipe.srs.orchestration.events.EventMessage;
import org.systems.dipe.srs.orchestration.events.EventType;
import org.systems.dipe.srs.orchestration.events.EventTypeProvider;

@Component
@AllArgsConstructor
public class FlowRunnerEvaluator {

    private final RuntimeService runtimeService;
    private final EventTypeProvider typeProvider;

    /**
     * Create a new camunda process.
     */
    public void runFlow(Event event) {
        EventMessage message = event.getMessage();
        EventType type = typeProvider.provide(message.getType());

        runtimeService.startProcessInstanceByKey(type.getFlow(), message.variables());
    }

}
