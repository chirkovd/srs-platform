package org.systems.dipe.srs.location;

import java.time.ZonedDateTime;

public record Comment(String commentId, String pointId, String authorId, String comment, ZonedDateTime created) {
}
