package org.systems.dipe.srs.orchestration.external.impl;

import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;
import org.systems.dipe.srs.orchestration.external.RequestsFacade;
import org.systems.dipe.srs.request.RequestsClient;

@Component
@AllArgsConstructor
@ConditionalOnBean(RequestsClient.class)
public class RequestsFacadeImpl implements RequestsFacade {

    private final RequestsClient requestsClient;

    @Override
    public void approve(String requestId) {
        requestsClient.approve(requestId);
    }
}
