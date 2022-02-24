package org.systems.dipe.srs.search;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Getter
@Builder
@AllArgsConstructor
public class SearchProcessSearch {
    private final Set<String> searchIds;
    private final Set<String> requestIds;
    private final boolean withDetails;
}
