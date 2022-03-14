package org.systems.dipe.srs.orchestration.flows.request.messages;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.systems.dipe.srs.orchestration.SrsEventType;

@Getter
@Setter
@NoArgsConstructor
public class SubmitRequestMessage extends AbstractRequestMessage {

    @JsonCreator
    public SubmitRequestMessage(String requestId) {
        super(requestId);
    }

    @Override
    public String getType() {
        return SrsEventType.REQUEST_FLOW_STARTED.getId();
    }

    @Override
    public boolean isRunner() {
        return true;
    }
}
