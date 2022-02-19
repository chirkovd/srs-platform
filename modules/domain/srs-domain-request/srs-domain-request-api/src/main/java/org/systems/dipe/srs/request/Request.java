package org.systems.dipe.srs.request;

import java.time.ZonedDateTime;
import java.util.List;

public record Request(String requestId, String customerId, String supervisorId, ZonedDateTime created,
                      ZonedDateTime approved, List<RequestItem> items, List<RequestLocation> locations) {

}
