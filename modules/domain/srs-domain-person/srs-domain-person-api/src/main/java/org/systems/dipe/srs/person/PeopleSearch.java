package org.systems.dipe.srs.person;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Getter
@Builder
@AllArgsConstructor
public final class PeopleSearch {
    private final Set<String> personIds;
    private final boolean withDetails;

}
