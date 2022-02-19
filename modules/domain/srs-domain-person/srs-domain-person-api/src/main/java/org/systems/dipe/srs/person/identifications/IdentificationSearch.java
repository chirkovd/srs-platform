package org.systems.dipe.srs.person.identifications;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Getter
@Builder
@AllArgsConstructor
public final class IdentificationSearch {
    private final Set<String> personIds;
    private final Set<String> values;
    private final Set<String> types;

}
