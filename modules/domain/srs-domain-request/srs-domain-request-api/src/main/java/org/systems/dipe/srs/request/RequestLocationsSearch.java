package org.systems.dipe.srs.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Getter
@Builder
@AllArgsConstructor
public class RequestLocationsSearch {
    private final Set<String> locationIds;
    private final Set<String> requestsIds;
}
