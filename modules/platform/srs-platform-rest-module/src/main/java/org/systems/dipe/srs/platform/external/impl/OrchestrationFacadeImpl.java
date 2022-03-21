package org.systems.dipe.srs.platform.external.impl;

import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.systems.dipe.srs.orchestration.OrchestrationClient;
import org.systems.dipe.srs.platform.external.OrchestrationFacade;

@Transactional
@AllArgsConstructor
@Component("platformOrchestrationFacade")
@ConditionalOnBean(OrchestrationClient.class)
public class OrchestrationFacadeImpl implements OrchestrationFacade {

    private final OrchestrationClient client;

    @Override
    public void submitRequest(String requestId) {
        client.submitRequest(requestId);
    }

    @Override
    public void assignRequest(String requestId, String supervisorId) {
        client.assignRequest(requestId, supervisorId);
    }

    @Override
    public void approveRequest(String requestId, String supervisorId) {
        client.approveRequest(requestId, supervisorId);
    }

    @Override
    public void assignSquad(String requestId, String searchId) {
        client.assignSquad(requestId, searchId);
    }

    @Override
    public void completeSearchProcess(String requestId, String searchId) {
        client.completeSearchProcess(requestId, searchId);
    }
}
