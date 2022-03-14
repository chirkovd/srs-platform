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
public class SearchCompleteMessage extends AbstractSearchMessage {

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public SearchCompleteMessage(
            @JsonProperty("requestId") String requestId,
            @JsonProperty("searchId") String searchId
    ) {
        super(requestId, searchId);
    }

    @Override
    public String getType() {
        return SrsEventType.SEARCH_COMPLETED.getId();
    }
}
