package org.systems.dipe.srs.person.roles;

import java.time.ZonedDateTime;

public record Role(String roleId, String role, String description, ZonedDateTime created) {

}
