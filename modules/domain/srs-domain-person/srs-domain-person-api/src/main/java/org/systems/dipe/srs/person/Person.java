package org.systems.dipe.srs.person;

import lombok.*;
import org.systems.dipe.srs.person.contacts.Contact;
import org.systems.dipe.srs.person.identifications.Identification;
import org.systems.dipe.srs.person.roles.Role;

import java.time.ZonedDateTime;
import java.util.Collection;

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

    private Collection<Role> roles;
    private Collection<Contact> contacts;
    private Collection<Identification> identifications;
}
