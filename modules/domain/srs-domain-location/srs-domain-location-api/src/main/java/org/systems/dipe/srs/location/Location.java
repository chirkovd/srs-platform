package org.systems.dipe.srs.location;

import java.time.ZonedDateTime;

public record Location(String locationId, ZonedDateTime created) {
}
