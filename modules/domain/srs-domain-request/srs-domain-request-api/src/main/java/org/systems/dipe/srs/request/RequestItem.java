package org.systems.dipe.srs.request;

import java.time.ZonedDateTime;

public record RequestItem(String itemId, String requestId, String targetId, ZonedDateTime created,
                          ZonedDateTime approved) {
}
