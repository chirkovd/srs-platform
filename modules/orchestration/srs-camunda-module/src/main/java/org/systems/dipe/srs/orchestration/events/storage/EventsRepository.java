package org.systems.dipe.srs.orchestration.events.storage;

import org.systems.dipe.srs.orchestration.events.Event;
import org.systems.dipe.srs.orchestration.events.EventMessage;
import org.systems.dipe.srs.orchestration.events.EventStatus;

import java.util.Collection;

public interface EventsRepository {

    <T extends EventMessage> void save(Event<T> event);

    Collection<Event> loadNewEvents();

    void setStatus(Collection<Integer> eventIds, EventStatus status);

    void failEvent(Integer eventId, String exception);

    Collection<Event> loadRetriedEvents();
}
