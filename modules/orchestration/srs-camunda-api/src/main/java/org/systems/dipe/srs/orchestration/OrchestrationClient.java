package org.systems.dipe.srs.orchestration;

public interface OrchestrationClient {

    void submitRequest(String requestId);

    void approveRequest(String requestId);

    void completeRequest(String requestId);

    void cancelRequest(String requestId);

    void submitSearchProcess(String requestId, String searchId);

    void cancelSearchProcess(String requestId, String searchId);

    void completeSearchProcess(String requestId, String searchId);

    void assignSquad(String requestId, String searchId);

}
