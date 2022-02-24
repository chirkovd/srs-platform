package org.systems.dipe.srs.search;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Getter
@Builder
@AllArgsConstructor
public class SearchSquadsSearch {
    private final Set<String> squadIds;
    private final Set<String> searchIds;

}
