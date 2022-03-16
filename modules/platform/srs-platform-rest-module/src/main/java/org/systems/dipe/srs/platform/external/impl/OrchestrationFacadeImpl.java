package org.systems.dipe.srs.platform.external.impl;

import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;
import org.systems.dipe.srs.orchestration.OrchestrationClient;
import org.systems.dipe.srs.platform.external.OrchestrationFacade;

@Component
@AllArgsConstructor
@ConditionalOnBean(OrchestrationClient.class)
public class OrchestrationFacadeImpl implements OrchestrationFacade {

    private final OrchestrationClient client;

    @Override
    public void submitRequest(String requestId) {
        client.submitRequest(requestId);
    }
}
