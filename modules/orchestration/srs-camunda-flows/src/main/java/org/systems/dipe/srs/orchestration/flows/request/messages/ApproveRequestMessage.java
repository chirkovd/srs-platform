package org.systems.dipe.srs.orchestration.flows.request.messages;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.systems.dipe.srs.orchestration.SrsEventType;
import org.systems.dipe.srs.orchestration.SrsVariables;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
public class ApproveRequestMessage extends AbstractRequestMessage {

    @Getter
    private String supervisorId;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public ApproveRequestMessage(
            @JsonProperty("requestId") String requestId,
            @JsonProperty("supervisorId") String supervisorId
    ) {
        super(requestId);
        this.supervisorId = supervisorId;
    }

    @Override
    public Map<String, Object> subscription() {
        Map<String, Object> variables = new HashMap<>(super.subscription());
        variables.put(SrsVariables.SUPERVISOR_ID, supervisorId);
        return variables;
    }

    @Override
    public String getType() {
        return SrsEventType.REQUEST_APPROVED.getId();
    }
}
