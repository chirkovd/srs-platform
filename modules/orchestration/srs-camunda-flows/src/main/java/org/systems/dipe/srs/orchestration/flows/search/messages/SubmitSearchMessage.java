package org.systems.dipe.srs.orchestration.flows.search.messages;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.systems.dipe.srs.orchestration.SrsEventType;

@Getter
@Setter
@NoArgsConstructor
public class SubmitSearchMessage extends AbstractSearchMessage {

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public SubmitSearchMessage(
            @JsonProperty("requestId") String requestId,
            @JsonProperty("searchId") String searchId
    ) {
        super(requestId, searchId);
    }

    @Override
    public String getType() {
        return SrsEventType.SEARCH_FLOW_STARTED.getId();
    }

    @Override
    public boolean isRunner() {
        return true;
    }
}
