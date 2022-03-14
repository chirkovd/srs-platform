package org.systems.dipe.srs.orchestration.flows.request.messages;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.NoArgsConstructor;
import org.systems.dipe.srs.orchestration.SrsEventType;

@NoArgsConstructor
public class CancelRequestMessage extends AbstractRequestMessage {

    @JsonCreator
    public CancelRequestMessage(String requestId) {
        super(requestId);
    }

    @Override
    public String getType() {
        return SrsEventType.REQUEST_CANCELLED.getId();
    }
}
