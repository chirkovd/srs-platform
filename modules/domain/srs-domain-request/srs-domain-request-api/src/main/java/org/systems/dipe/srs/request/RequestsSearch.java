package org.systems.dipe.srs.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Getter
@Builder
@AllArgsConstructor
public class RequestsSearch {
    private final Set<String> requestIds;
    private final Set<String> customerIds;
    private final Set<String> supervisorIds;
    private final boolean withDetails;
}
