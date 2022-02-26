package org.systems.dipe.srs.orchestration;

import org.springframework.stereotype.Component;
import org.systems.dipe.srs.orchestration.events.EventType;
import org.systems.dipe.srs.orchestration.events.EventTypeProvider;

@Component
public class SrsEventTypeProvider implements EventTypeProvider {
    @Override
    public EventType provide(String type) {
        return SrsEventType.parseById(type);
    }
}
