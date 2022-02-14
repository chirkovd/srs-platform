package org.systems.dipe.srs.person.roles;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;
import java.util.Objects;

@Getter
@Builder
@AllArgsConstructor
public class RoleLink {

    private final String roleId;
    private final String personId;
    private final ZonedDateTime created;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RoleLink roleLink = (RoleLink)o;
        return roleId.equals(roleLink.roleId) && personId.equals(roleLink.personId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, personId);
    }
}
