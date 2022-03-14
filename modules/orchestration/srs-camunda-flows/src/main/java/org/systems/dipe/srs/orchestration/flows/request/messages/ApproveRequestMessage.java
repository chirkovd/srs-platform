package org.systems.dipe.srs.orchestration.flows.request.messages;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.NoArgsConstructor;
import org.systems.dipe.srs.orchestration.SrsEventType;

@NoArgsConstructor
public class ApproveRequestMessage extends AbstractRequestMessage {

    @JsonCreator
    public ApproveRequestMessage(String requestId) {
        super(requestId);
    }

    @Override
    public String getType() {
        return SrsEventType.REQUEST_APPROVED.getId();
    }
}
