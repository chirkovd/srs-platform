package org.systems.dipe.srs.location;

import java.time.ZonedDateTime;

public record Point(String pointId, String locationId, double x, double y, ZonedDateTime created) {
}
