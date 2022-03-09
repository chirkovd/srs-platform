package org.systems.dipe.srs.orchestration.flows.request.messages;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.systems.dipe.srs.orchestration.SrsVariables;
import org.systems.dipe.srs.orchestration.events.EventMessage;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApproveRequestMessage implements EventMessage {

    private String requestId;

    @Override
    public Map<String, Object> subscription() {
        return Map.of(SrsVariables.REQUEST_ID, requestId);
    }
}
