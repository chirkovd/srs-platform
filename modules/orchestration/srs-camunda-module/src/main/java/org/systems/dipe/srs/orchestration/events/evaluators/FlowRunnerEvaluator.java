package org.systems.dipe.srs.orchestration.events.evaluators;

import lombok.AllArgsConstructor;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.stereotype.Component;
import org.systems.dipe.srs.orchestration.events.Event;
import org.systems.dipe.srs.orchestration.events.EventMessage;
import org.systems.dipe.srs.orchestration.events.EventType;

@Component
@AllArgsConstructor
public class FlowRunnerEvaluator {

    private final RuntimeService runtimeService;

    /**
     * Create a new camunda process.
     */
    public void runFlow(Event event) {
        EventType type = event.getType();
        EventMessage message = event.getMessage();

        runtimeService.startProcessInstanceByKey(type.getFlow(), message.variables());
    }

}
