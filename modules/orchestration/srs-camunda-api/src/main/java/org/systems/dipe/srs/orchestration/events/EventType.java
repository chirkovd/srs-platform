package org.systems.dipe.srs.orchestration.events;

public interface EventType {

    String getId();

    String getFlow();

    Class<? extends EventMessage> getMessageType();
}
