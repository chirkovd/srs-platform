package org.systems.dipe.srs.location;

import java.time.ZonedDateTime;
import java.util.List;

public record Location(String locationId, ZonedDateTime created, List<Point> points) {
}
