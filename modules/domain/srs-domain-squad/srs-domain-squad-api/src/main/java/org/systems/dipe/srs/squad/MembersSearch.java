package org.systems.dipe.srs.squad;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Getter
@Builder
@AllArgsConstructor
public class MembersSearch {
    private final Set<String> memberIds;
    private final Set<String> squadIds;
    private final Set<String> volunteerIds;
}
