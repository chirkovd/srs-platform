package org.systems.dipe.srs.squad;

import java.time.ZonedDateTime;

public record Equipment(String inventoryId, String squadId, ZonedDateTime created) {
}
