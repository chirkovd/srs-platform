package org.systems.dipe.srs.request;

import java.time.ZonedDateTime;

public record Request(String requestId, String customerId, String supervisorId, ZonedDateTime created,
                      ZonedDateTime approved) {

}
