package org.systems.dipe.srs.orchestration.events.storage.jooq;

import lombok.AllArgsConstructor;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.systems.dipe.srs.orchestration.events.Event;
import org.systems.dipe.srs.orchestration.events.EventMessage;
import org.systems.dipe.srs.orchestration.events.EventStatus;
import org.systems.dipe.srs.orchestration.events.storage.EventsRepository;
import org.systems.dipe.srs.orchestration.events.storage.mapper.EventsMapper;
import org.systems.dipe.srs.orchestration.tables.JEvent;
import org.systems.dipe.srs.utils.TimeUtils;

import java.util.Collection;

@Repository
@Transactional
@AllArgsConstructor
public class EventsJooqRepository implements EventsRepository {

    private final DefaultDSLContext dsl;
    private final EventsMapper mapper;

    @Override
    public <T extends EventMessage> void save(Event<T> event) {
        dsl.insertInto(JEvent.EVENT)
                .set(mapper.toJooq(event))
                .execute();
    }

    @Override
    public Collection<Event> loadNewEvents() {
        return dsl.selectFrom(JEvent.EVENT)
                .where(JEvent.EVENT.STATUS.eq(EventStatus.NEW.name()))
                .orderBy(JEvent.EVENT.CREATED.asc())
                .limit(100)
                .fetch()
                .map(mapper::fromJooq);
    }

    @Override
    public void setStatus(Collection<Integer> eventIds, EventStatus status) {
        dsl.update(JEvent.EVENT)
                .set(JEvent.EVENT.STATUS, status.name())
                .where(JEvent.EVENT.EVENT_ID.in(eventIds))
                .execute();
    }

    @Override
    public void failEvent(Integer eventId, String exception) {
        dsl.update(JEvent.EVENT)
                .set(JEvent.EVENT.STATUS, EventStatus.ERROR.name())
                .where(JEvent.EVENT.EVENT_ID.eq(eventId))
                .execute();
    }

    @Override
    public Collection<Event> loadRetriedEvents() {
        return dsl.selectFrom(JEvent.EVENT)
                .where(JEvent.EVENT.RETRY_AT.lessOrEqual(TimeUtils.fromZdt(TimeUtils.now())),
                        JEvent.EVENT.STATUS.eq(EventStatus.RETRY.name()))
                .fetch()
                .map(mapper::fromJooq);
    }
}
