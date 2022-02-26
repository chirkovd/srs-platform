package org.systems.dipe.srs.orchestration.events;

public interface EventType {

    String getId();

    Class<? extends EventMessage> message();
}
