package org.systems.dipe.srs.search;

import java.time.ZonedDateTime;

public record SearchLocation(String locationId, String searchId, ZonedDateTime created) {
}
