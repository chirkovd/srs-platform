package org.systems.dipe.srs.orchestration.events;

public enum EventStatus {
    NEW,
    PROCESSING,
    RETRY,
    ERROR,
    COMPLETE
}
