package org.systems.dipe.srs.person.roles;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Getter
@Builder
@AllArgsConstructor
public class RolesSearch {
    private final Set<String> roleIds;
}
