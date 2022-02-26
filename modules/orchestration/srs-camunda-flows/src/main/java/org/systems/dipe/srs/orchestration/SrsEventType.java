package org.systems.dipe.srs.orchestration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.systems.dipe.srs.orchestration.events.EventMessage;
import org.systems.dipe.srs.orchestration.events.EventType;

import java.util.Objects;

@Getter
@AllArgsConstructor
public enum SrsEventType implements EventType {
    START_REQUEST_FLOW(null),
    REQUEST_APPROVED(null),
    REQUEST_COMPLETED(null);

    private final Class<? extends EventMessage> messageType;

    @Override
    public String getId() {
        return name();
    }

    public static SrsEventType parseById(String type) {
        for (SrsEventType eventType : values()) {
            if (Objects.equals(type, eventType.getId())) {
                return eventType;
            }
        }
        throw new IllegalArgumentException("Cannot parse event type by " + type);
    }

}
