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
public class RequestItem {
    private String itemId;
    private String requestId;
    private String targetId;
    private ZonedDateTime created;
    private ZonedDateTime approved;
    private ZonedDateTime dismissed;
}
