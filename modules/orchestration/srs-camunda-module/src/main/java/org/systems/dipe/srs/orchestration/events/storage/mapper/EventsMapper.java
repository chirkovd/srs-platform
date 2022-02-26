package org.systems.dipe.srs.orchestration.events.storage.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.systems.dipe.srs.mappers.CommonMapper;
import org.systems.dipe.srs.orchestration.events.Event;
import org.systems.dipe.srs.orchestration.events.EventStatus;
import org.systems.dipe.srs.orchestration.events.EventType;
import org.systems.dipe.srs.orchestration.events.EventTypeProvider;
import org.systems.dipe.srs.orchestration.tables.records.JEventRecord;

import java.util.Objects;

@Slf4j
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public abstract class EventsMapper implements CommonMapper {

    private ObjectMapper objectMapper;
    private EventTypeProvider eventTypeProvider;

    @Mapping(target = "message", ignore = true)
    @Mapping(target = "error", ignore = true)
    @Mapping(target = "retryAt", ignore = true)
    abstract JEventRecord toJooq(Event event);

    @AfterMapping
    protected void afterMapping(@MappingTarget JEventRecord record, Event event) {
        if (Objects.isNull(event.getMessage())) {
            return;
        }
        try {
            record.setMessage(objectMapper.writeValueAsString(event.getMessage()));
        } catch (JsonProcessingException e) {
            record.setError(e.getMessage());
            record.setStatus(EventStatus.ERROR.name());
        }
    }

    @Mapping(target = "message", ignore = true)
    abstract Event fromJooq(JEventRecord record);

    @AfterMapping
    protected void afterMapping(@MappingTarget Event event, JEventRecord record) {
        if (Objects.isNull(record.getMessage())) {
            return;
        }
        event.setMessage(objectMapper.convertValue(record.getMessage(), event.getType().message()));
    }

    protected String fromEventType(EventType eventType) {
        return eventType.getId();
    }

    protected EventType toEventType(String eventType) {
        return eventTypeProvider.provide(eventType);
    }

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Autowired
    public void setEventTypeProvider(EventTypeProvider eventTypeProvider) {
        this.eventTypeProvider = eventTypeProvider;
    }
}
