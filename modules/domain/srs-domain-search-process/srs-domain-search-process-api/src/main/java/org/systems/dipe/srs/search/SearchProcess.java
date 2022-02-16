package org.systems.dipe.srs.search;

import java.time.ZonedDateTime;

public record SearchProcess(String searchId, String requestId, SearchProcessStatus status, ZonedDateTime created) {
}
