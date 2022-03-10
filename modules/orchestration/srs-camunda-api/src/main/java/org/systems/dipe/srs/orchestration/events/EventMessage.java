package org.systems.dipe.srs.orchestration.events;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.Collections;
import java.util.Map;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.CLASS
)
public interface EventMessage {

    String getType();

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
