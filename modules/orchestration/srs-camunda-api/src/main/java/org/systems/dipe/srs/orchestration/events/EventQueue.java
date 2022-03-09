package org.systems.dipe.srs.orchestration.events;

public interface EventQueue {

    <T extends EventMessage> void pushEvent(Event<T> event);

}
