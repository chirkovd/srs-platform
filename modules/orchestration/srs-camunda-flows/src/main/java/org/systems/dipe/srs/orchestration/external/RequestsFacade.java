package org.systems.dipe.srs.orchestration.external;

import org.systems.dipe.srs.request.Request;

public interface RequestsFacade {

    void approve(String requestId);

    Request find(String requestId);
}
