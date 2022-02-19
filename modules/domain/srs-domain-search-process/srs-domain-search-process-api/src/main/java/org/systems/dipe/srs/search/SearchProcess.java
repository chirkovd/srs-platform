package org.systems.dipe.srs.search;

import java.time.ZonedDateTime;
import java.util.List;

public record SearchProcess(String searchId, String requestId, SearchProcessStatus status, ZonedDateTime created,
                            List<SearchLocation> locations, List<SearchSquad> squads) {
}
