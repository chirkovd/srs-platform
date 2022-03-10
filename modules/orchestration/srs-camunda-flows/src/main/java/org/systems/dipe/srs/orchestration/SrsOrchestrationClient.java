package org.systems.dipe.srs.orchestration;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.systems.dipe.srs.orchestration.events.EventQueue;
import org.systems.dipe.srs.orchestration.flows.request.messages.ApproveRequestMessage;
import org.systems.dipe.srs.orchestration.flows.request.messages.CompleteRequestMessage;
import org.systems.dipe.srs.orchestration.flows.request.messages.SubmitRequestMessage;

@Service
@AllArgsConstructor
public class SrsOrchestrationClient implements OrchestrationClient {

    private final EventQueue eventQueue;

    @Override
    public void submitRequest(String requestId) {
        eventQueue.pushMessage(new SubmitRequestMessage(requestId));
    }

    @Override
    public void approveRequest(String requestId) {
        eventQueue.pushMessage(new ApproveRequestMessage(requestId));
    }

    @Override
    public void completeRequest(String requestId) {
        eventQueue.pushMessage(new CompleteRequestMessage(requestId));
    }
}
