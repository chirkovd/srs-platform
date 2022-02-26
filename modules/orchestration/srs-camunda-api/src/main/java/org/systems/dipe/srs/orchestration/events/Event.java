package org.systems.dipe.srs.orchestration.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Event<T extends EventMessage> {

    private long eventId;
    private EventStatus status;
    private EventType type;
    private T message;
    private ZonedDateTime created;

}
