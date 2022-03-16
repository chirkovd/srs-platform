package org.systems.dipe.srs.person;

import lombok.*;
import org.systems.dipe.srs.person.contacts.Contact;
import org.systems.dipe.srs.person.identifications.Identification;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public final class Person {
    private String personId;
    private String firstName;
    private String lastName;
    private ZonedDateTime created;

    private Set<String> roleIds;
    private List<Contact> contacts;
    private Set<Identification> identifications;
}
