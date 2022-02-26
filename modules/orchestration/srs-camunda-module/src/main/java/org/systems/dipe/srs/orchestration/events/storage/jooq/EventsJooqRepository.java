package org.systems.dipe.srs.orchestration.events.storage.jooq;

import lombok.AllArgsConstructor;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.systems.dipe.srs.orchestration.events.storage.EventsRepository;

@Repository
@Transactional
@AllArgsConstructor
public class EventsJooqRepository implements EventsRepository {

    private final DefaultDSLContext dsl;


}
