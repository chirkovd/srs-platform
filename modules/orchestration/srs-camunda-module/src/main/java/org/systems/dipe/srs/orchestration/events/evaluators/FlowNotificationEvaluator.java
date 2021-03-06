package org.systems.dipe.srs.orchestration.events.evaluators;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.Execution;
import org.camunda.bpm.engine.runtime.ExecutionQuery;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.runtime.ProcessInstanceQuery;
import org.springframework.stereotype.Component;
import org.systems.dipe.srs.orchestration.events.*;

import java.util.List;
import java.util.Objects;

@Slf4j
@Component
@AllArgsConstructor
public class FlowNotificationEvaluator {

    private final RuntimeService runtimeService;
    private final EventTypeProvider typeProvider;

    /**
     * Search active execution process that is waiting for message.
     * Find subscription and notify flow.
     */
    public void notifyFlow(Event event) {
        EventMessage message = event.getMessage();
        EventType type = typeProvider.provide(message.getType());
        String messageName = message.getClass().getSimpleName();

        ExecutionQuery executionQuery = runtimeService.createExecutionQuery()
                .messageEventSubscriptionName(messageName)
                .processDefinitionKey(type.getFlow())
                .active();

        message.subscription().forEach(executionQuery::processVariableValueEquals);

        Execution execution = executionQuery.singleResult();
        if (Objects.isNull(execution)) {
            ProcessInstanceQuery instanceQuery = runtimeService.createProcessInstanceQuery()
                    .processDefinitionKey(type.getFlow());

            message.subscription().forEach(instanceQuery::variableValueEquals);

            List<ProcessInstance> instances = instanceQuery.list();
            if (CollectionUtils.isEmpty(instances)) {
                log.warn("There is no active subscription and active processes, skip event");
            } else {
                throw new RetryEventException("Subscription is not found, retry later");
            }
        } else {
            runtimeService.messageEventReceived(messageName, execution.getId(), message.variables());
        }
    }

}
