package org.systems.dipe.srs.person;

import java.time.ZonedDateTime;

public record Person(String personId, String username, ZonedDateTime created) {

}
