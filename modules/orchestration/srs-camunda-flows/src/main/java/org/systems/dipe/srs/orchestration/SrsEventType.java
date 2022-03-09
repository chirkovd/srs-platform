package org.systems.dipe.srs.orchestration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.systems.dipe.srs.orchestration.events.EventMessage;
import org.systems.dipe.srs.orchestration.events.EventType;
import org.systems.dipe.srs.orchestration.flows.request.messages.ApproveRequestMessage;
import org.systems.dipe.srs.orchestration.flows.request.messages.CompleteRequestMessage;
import org.systems.dipe.srs.orchestration.flows.request.messages.SubmitRequestMessage;

import java.util.Objects;

@Getter
@AllArgsConstructor
public enum SrsEventType implements EventType {
    REQUEST_FLOW_STARTED(SrsVariables.REQUEST_FLOW, SubmitRequestMessage.class),
    REQUEST_APPROVED(SrsVariables.REQUEST_FLOW, ApproveRequestMessage.class),
    REQUEST_COMPLETED(SrsVariables.REQUEST_FLOW, CompleteRequestMessage.class);

    private final String flow;
    private final Class<? extends EventMessage> messageType;

    @Override
    public String getId() {
        return name();
    }

    public static SrsEventType parseById(String type) {
        for (SrsEventType eventType : values()) {
            if (Objects.equals(type, eventType.getId())) {
                return eventType;
            }
        }
        throw new IllegalArgumentException("Cannot parse event type by " + type);
    }

}
