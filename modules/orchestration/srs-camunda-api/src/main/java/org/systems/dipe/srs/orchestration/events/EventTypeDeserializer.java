package org.systems.dipe.srs.orchestration.events;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class EventTypeDeserializer extends StdDeserializer<EventType> {

    private static final long serialVersionUID = 6047766636244304368L;

    private final EventTypeProvider provider;

    public EventTypeDeserializer(EventTypeProvider provider) {
        super(EventType.class);
        this.provider = provider;
    }

    @Override
    public EventType deserialize(JsonParser p, DeserializationContext ctx) throws IOException {
        return provider.provide(p.getValueAsString());
    }
}
