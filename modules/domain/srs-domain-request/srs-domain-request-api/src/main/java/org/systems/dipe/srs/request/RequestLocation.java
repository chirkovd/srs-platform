package org.systems.dipe.srs.request;

import java.time.ZonedDateTime;

public record RequestLocation(String locationId, String requestId, ZonedDateTime created) {
}
