package org.systems.dipe.srs.orchestration.events;

public interface EventTypeProvider {
    EventType provide(String type);
}
