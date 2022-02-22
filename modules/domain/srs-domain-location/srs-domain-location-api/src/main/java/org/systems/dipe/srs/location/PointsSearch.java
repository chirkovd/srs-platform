package org.systems.dipe.srs.location;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Collection;

@Getter
@Builder
@AllArgsConstructor
public class PointsSearch {

    private final Collection<String> pointIds;
    private final Collection<String> locationIds;
}
