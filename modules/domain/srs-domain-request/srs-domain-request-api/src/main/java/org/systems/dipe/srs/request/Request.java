package org.systems.dipe.srs.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Request {
    private String requestId;
    private String customerId;
    private String supervisorId;
    private ZonedDateTime created;
    private ZonedDateTime approved;
    private List<RequestItem> items;
    private List<RequestLocation> locations;
}
