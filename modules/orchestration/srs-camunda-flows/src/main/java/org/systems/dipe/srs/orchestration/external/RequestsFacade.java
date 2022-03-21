package org.systems.dipe.srs.orchestration.external;

import org.systems.dipe.srs.request.Request;

public interface RequestsFacade {

    void assign(String requestId, String supervisorId);

    void approve(String requestId, String supervisorId);

    Request find(String requestId);
}
