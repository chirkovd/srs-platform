package org.systems.dipe.srs.orchestration.events;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Collections;
import java.util.Map;

public interface EventMessage {

    @JsonIgnore
    default boolean isRunner() {
        return false;
    }

    @JsonIgnore
    default Map<String, Object> variables() {
        return Collections.emptyMap();
    }

    @JsonIgnore
    default Map<String, Object> subscription() {
        return Collections.emptyMap();
    }
}
