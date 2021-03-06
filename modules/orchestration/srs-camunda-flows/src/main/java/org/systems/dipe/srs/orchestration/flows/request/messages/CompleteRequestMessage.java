package org.systems.dipe.srs.orchestration.flows.request.messages;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.NoArgsConstructor;
import org.systems.dipe.srs.orchestration.SrsEventType;

@NoArgsConstructor
public class CompleteRequestMessage extends AbstractRequestMessage {

    @JsonCreator
    public CompleteRequestMessage(String requestId) {
        super(requestId);
    }

    @Override
    public String getType() {
        return SrsEventType.REQUEST_COMPLETED.getId();
    }

}
