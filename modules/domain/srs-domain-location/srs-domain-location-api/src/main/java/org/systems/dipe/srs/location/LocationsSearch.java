package org.systems.dipe.srs.location;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Getter
@Builder
@AllArgsConstructor
public final class LocationsSearch {
    private final Set<String> locationIds;

}
