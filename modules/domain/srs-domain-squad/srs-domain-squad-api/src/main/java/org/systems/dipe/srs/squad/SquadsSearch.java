package org.systems.dipe.srs.squad;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Getter
@Builder
@AllArgsConstructor
public class SquadsSearch {
    private final Set<String> squadIds;
    private final boolean withDetails;
}
