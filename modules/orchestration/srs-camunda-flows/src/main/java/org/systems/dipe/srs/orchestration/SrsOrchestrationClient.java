package org.systems.dipe.srs.orchestration;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.systems.dipe.srs.orchestration.events.EventQueue;
import org.systems.dipe.srs.orchestration.flows.request.messages.*;
import org.systems.dipe.srs.orchestration.flows.search.messages.SearchCancelMessage;
import org.systems.dipe.srs.orchestration.flows.search.messages.SearchCompleteMessage;
import org.systems.dipe.srs.orchestration.flows.search.messages.SearchSquadAssignMessage;
import org.systems.dipe.srs.orchestration.flows.search.messages.SubmitSearchMessage;

@Service
@AllArgsConstructor
public class SrsOrchestrationClient implements OrchestrationClient {

    private final EventQueue eventQueue;

    @Override
    public void submitRequest(String requestId) {
        eventQueue.pushMessage(new SubmitRequestMessage(requestId));
    }

    @Override
    public void assignRequest(String requestId, String supervisorId) {
        eventQueue.pushMessage(new AssignRequestMessage(requestId, supervisorId));
    }

    @Override
    public void approveRequest(String requestId, String supervisorId) {
        eventQueue.pushMessage(new ApproveRequestMessage(requestId, supervisorId));
    }

    @Override
    public void completeRequest(String requestId) {
        eventQueue.pushMessage(new CompleteRequestMessage(requestId));
    }

    @Override
    public void cancelRequest(String requestId) {
        eventQueue.pushMessage(new CancelRequestMessage(requestId));
    }

    @Override
    public void submitSearchProcess(String requestId, String searchId) {
        eventQueue.pushMessage(new SubmitSearchMessage(requestId, searchId));
    }

    @Override
    public void cancelSearchProcess(String requestId, String searchId) {
        eventQueue.pushMessage(new SearchCancelMessage(requestId, searchId));
    }

    @Override
    public void completeSearchProcess(String requestId, String searchId) {
        eventQueue.pushMessage(new SearchCompleteMessage(requestId, searchId));
    }

    @Override
    public void assignSquad(String requestId, String searchId) {
        eventQueue.pushMessage(new SearchSquadAssignMessage(requestId, searchId));
    }
}
