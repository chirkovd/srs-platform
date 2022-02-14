package org.systems.dipe.srs.person.contacts;

import java.util.Set;

public record ContactSearch(Set<String> contactIds, Set<String> personIds) {

}
