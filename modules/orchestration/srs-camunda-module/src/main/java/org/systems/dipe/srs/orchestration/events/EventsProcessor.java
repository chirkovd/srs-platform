package org.systems.dipe.srs.orchestration.events;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.systems.dipe.srs.orchestration.events.evaluators.EventsEvaluator;
import org.systems.dipe.srs.orchestration.events.storage.EventsRepository;
import org.systems.dipe.srs.utils.GroupUtils;
import org.systems.dipe.srs.utils.TimeUtils;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;

@Slf4j
@Service
@AllArgsConstructor
public class EventsProcessor implements EventQueue {

    private final EventsRepository repository;
    private final EventsEvaluator evaluator;

    @Override
    @Transactional
    public <T extends EventMessage> void pushEvent(Event<T> event) {
        if (Objects.isNull(event.getCreated())) {
            event.setCreated(TimeUtils.now());
        }
        if (Objects.isNull(event.getStatus())) {
            event.setStatus(EventStatus.NEW);
        }
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
     */
    private int processEvents(Collection<Event> events) {
        Set<Integer> eventIds = GroupUtils.extractUnique(events, Event::getEventId);
        repository.setStatus(eventIds, EventStatus.PROCESSING);

        int eventsCount = 0;
        for (Event event : events) {
            try {
                evaluator.evaluate(event);

                repository.setStatus(Set.of(event.getEventId()), EventStatus.COMPLETE);

                eventsCount++;
            } catch (RetryEventException re) {
                ZonedDateTime now = TimeUtils.now();
                if (event.getCreated().isBefore(now.minusMinutes(5))) {
                    repository.failEvent(event.getEventId(), "Cannot retry");
                } else {
                    log.debug("Retry event {} later, after 60 sec", event.getEventId());
                    repository.retryEvent(event.getEventId(), now.plusMinutes(1));
                }
            } catch (Exception e) {
                repository.failEvent(event.getEventId(), e.getMessage());
            }
        }
        return eventsCount;
    }

}
