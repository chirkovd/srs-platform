package org.systems.dipe.srs.person.contacts;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Getter
@Builder
@AllArgsConstructor
public final class ContactsSearch {
    private final Set<String> contactIds;
    private final Set<String> personIds;
}
