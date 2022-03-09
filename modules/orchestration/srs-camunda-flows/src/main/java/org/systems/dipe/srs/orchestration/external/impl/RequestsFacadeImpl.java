package org.systems.dipe.srs.orchestration.external.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.systems.dipe.srs.orchestration.external.RequestsFacade;
import org.systems.dipe.srs.request.RequestsClient;

@Component
@AllArgsConstructor
public class RequestsFacadeImpl implements RequestsFacade {

    private final RequestsClient requestsClient;

    @Override
    public void approve(String requestId) {
        requestsClient.approve(requestId);
    }
}
