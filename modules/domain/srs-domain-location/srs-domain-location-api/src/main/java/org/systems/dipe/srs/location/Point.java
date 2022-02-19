package org.systems.dipe.srs.location;

import java.time.ZonedDateTime;
import java.util.List;

public record Point(String pointId, String locationId, double x, double y, ZonedDateTime created,
                    List<Comment> comments) {
}
