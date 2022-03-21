package org.systems.dipe.srs.platform.external;

public interface OrchestrationFacade {

    void submitRequest(String requestId);

    void assignRequest(String requestId, String supervisorId);

    void approveRequest(String requestId, String supervisorId);

    void assignSquad(String requestId, String searchId);

    void completeSearchProcess(String requestId, String searchId);
}
