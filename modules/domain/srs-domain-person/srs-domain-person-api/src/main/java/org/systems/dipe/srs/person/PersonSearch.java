package org.systems.dipe.srs.person;

import java.util.Set;

public record PersonSearch(Set<String> personIds, Set<String> usernames) {

}
