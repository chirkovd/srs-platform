package org.systems.dipe.srs.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestLocation {
    private String locationId;
    private String requestId;
    private ZonedDateTime created;
}
