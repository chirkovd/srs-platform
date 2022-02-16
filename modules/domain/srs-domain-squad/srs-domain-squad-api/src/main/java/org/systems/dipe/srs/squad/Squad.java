package org.systems.dipe.srs.squad;

import java.time.ZonedDateTime;

public record Squad(String squadId, String processId, ZonedDateTime created) {
}
