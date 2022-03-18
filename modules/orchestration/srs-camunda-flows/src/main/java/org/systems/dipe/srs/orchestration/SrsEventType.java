package org.systems.dipe.srs.orchestration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.systems.dipe.srs.orchestration.events.EventType;

import java.util.Objects;

@Getter
@AllArgsConstructor
public enum SrsEventType implements EventType {
    REQUEST_FLOW_STARTED(SrsVariables.REQUEST_FLOW),
    REQUEST_ASSIGNED(SrsVariables.REQUEST_FLOW),
    REQUEST_APPROVED(SrsVariables.REQUEST_FLOW),
    REQUEST_COMPLETED(SrsVariables.REQUEST_FLOW),
    REQUEST_CANCELLED(SrsVariables.REQUEST_FLOW),

    SEARCH_FLOW_STARTED(SrsVariables.SEARCH_FLOW),
    SEARCH_SQUAD_CREATED(SrsVariables.SEARCH_FLOW),
    SEARCH_COMPLETED(SrsVariables.SEARCH_FLOW),
    SEARCH_CANCELLED(SrsVariables.SEARCH_FLOW),
    ;

    private final String flow;

    @Override
    public String getId() {
        return name().toLowerCase();
    }

    public static SrsEventType parseById(String type) {
        if (Objects.isNull(type)) {
            return null;
        }
        for (SrsEventType eventType : values()) {
            if (StringUtils.equalsIgnoreCase(type, eventType.getId())) {
                return eventType;
            }
        }
        throw new IllegalArgumentException("Cannot parse event type by " + type);
    }

}
