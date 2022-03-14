package org.systems.dipe.srs.orchestration.flows.search.messages;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NoArgsConstructor;
import org.systems.dipe.srs.orchestration.SrsEventType;

@NoArgsConstructor
public class SearchCancelMessage extends AbstractSearchMessage {

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public SearchCancelMessage(
            @JsonProperty("requestId") String requestId,
            @JsonProperty("searchId") String searchId
    ) {
        super(requestId, searchId);
    }

    @Override
    public String getType() {
        return SrsEventType.SEARCH_CANCELLED.getId();
    }


}
