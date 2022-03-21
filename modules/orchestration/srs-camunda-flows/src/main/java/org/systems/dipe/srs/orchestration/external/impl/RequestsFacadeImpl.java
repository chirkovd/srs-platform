package org.systems.dipe.srs.orchestration.external.impl;

import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;
import org.systems.dipe.srs.orchestration.external.RequestsFacade;
import org.systems.dipe.srs.request.Request;
import org.systems.dipe.srs.request.RequestsClient;
import org.systems.dipe.srs.request.RequestsSearch;

import java.util.Collection;
import java.util.Set;

@Component
@AllArgsConstructor
@ConditionalOnBean(RequestsClient.class)
public class RequestsFacadeImpl implements RequestsFacade {

    private final RequestsClient client;

    @Override
    public void assign(String requestId, String supervisorId) {
        client.assign(requestId, supervisorId);
    }

    @Override
    public void approve(String requestId, String supervisorId) {
        client.approve(requestId, supervisorId);
    }

    @Override
    public Request find(String requestId) {
        Collection<Request> requests = client.search(
                RequestsSearch.builder()
                        .requestIds(Set.of(requestId))
                        .withDetails(true)
                        .build()
        );
        return requests.isEmpty() ? null : requests.iterator().next();
    }
}
