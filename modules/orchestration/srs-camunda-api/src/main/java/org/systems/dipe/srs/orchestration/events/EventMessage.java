package org.systems.dipe.srs.orchestration.events;

import java.util.Collections;
import java.util.Map;

public interface EventMessage {

    default boolean isRunner() {
        return false;
    }

    default Map<String, Object> variables() {
        return Collections.emptyMap();
    }

    default Map<String, Object> subscription() {
        return Collections.emptyMap();
    }
}
