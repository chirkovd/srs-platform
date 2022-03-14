package org.systems.dipe.srs.orchestration.external;

import lombok.Getter;
import org.systems.dipe.srs.request.Request;

import java.util.HashSet;
import java.util.Set;

public class RequestsFacadeTestImpl implements RequestsFacade {

    @Getter
    private final Set<String> requests = new HashSet<>();

    @Override
    public void approve(String requestId) {
        requests.add(requestId);
    }

    @Override
    public Request find(String requestId) {
        Request request = new Request();
        request.setRequestId(requestId);
        return request;
    }
}
