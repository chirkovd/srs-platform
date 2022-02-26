package org.systems.dipe.srs.orchestration.events;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.systems.dipe.srs.orchestration.events.storage.EventsRepository;

@Service
@Transactional
@AllArgsConstructor
public class EventsProcessor {

    private final EventsRepository repository;

    public <T extends EventMessage> void pushEvent(Event<T> event) {

    }

}
