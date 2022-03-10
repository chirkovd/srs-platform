package org.systems.dipe.srs.orchestration.flows.request.messages;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.systems.dipe.srs.orchestration.SrsEventType;
import org.systems.dipe.srs.orchestration.SrsVariables;
import org.systems.dipe.srs.orchestration.events.EventMessage;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class CompleteRequestMessage implements EventMessage {

    private String requestId;

    @JsonCreator
    public CompleteRequestMessage(String requestId) {
        this.requestId = requestId;
    }

    @Override
    public Map<String, Object> subscription() {
        return Map.of(SrsVariables.REQUEST_ID, requestId);
    }

    @Override
    public String getType() {
        return SrsEventType.REQUEST_COMPLETED.getId();
    }

    @Override
    public Map<String, Object> variables() {
        return Map.of(SrsVariables.REQUEST_ID, requestId);
    }
}
