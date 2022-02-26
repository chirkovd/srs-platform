package org.systems.dipe.srs.orchestration.events;

import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.systems.dipe.srs.orchestration.events.storage.EventsRepository;
import org.systems.dipe.srs.utils.GroupUtils;

import java.util.Collection;
import java.util.Set;

@Service
@AllArgsConstructor
public class EventsProcessor {

    private final EventsRepository repository;
    private final EventsEvaluator evaluator;

    @Transactional
    public <T extends EventMessage> void pushEvent(Event<T> event) {
        repository.save(event);
    }

    @Transactional
    public int evaluateUnprocessedEvents() {
        Collection<Event> events = repository.loadNewEvents();
        if (CollectionUtils.isEmpty(events)) {
            return 0;
        }
        return processEvents(events);
    }

    @Transactional
    public int evaluateRetriedEvents() {
        Collection<Event> events = repository.loadRetriedEvents();
        if (CollectionUtils.isEmpty(events)) {
            return 0;
        }
        return processEvents(events);
    }

    /**
     * Process is not thread-safe. Use new transactions for concurrent processing.
     * */
    private int processEvents(Collection<Event> events) {
        Set<Integer> eventIds = GroupUtils.extractUnique(events, Event::getEventId);
        repository.setStatus(eventIds, EventStatus.PROCESSING);

        int eventsCount = 0;
        for (Event event : events) {
            try {
                evaluator.evaluate(event);

                repository.setStatus(Set.of(event.getEventId()), EventStatus.COMPLETE);

                eventsCount++;
            // TODO add special exception handler for retry.
            } catch (Exception e) {
                repository.failEvent(event.getEventId(), e.getMessage());
            }
        }
        return eventsCount;
    }

}
