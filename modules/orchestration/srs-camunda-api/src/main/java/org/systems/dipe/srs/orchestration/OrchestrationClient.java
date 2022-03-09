package org.systems.dipe.srs.orchestration;

public interface OrchestrationClient {

    void submitRequest(String requestId);

    void approveRequest(String requestId);

    void completeRequest(String requestId);

}
