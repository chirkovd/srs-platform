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

    private Integer eventId;
    private EventStatus status;
    private T message;
    private ZonedDateTime created;

    public Event(T message) {
        this.message = message;
    }

}
