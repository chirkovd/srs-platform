package org.systems.dipe.srs.person;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.systems.dipe.srs.person.contacts.Contact;
import org.systems.dipe.srs.person.roles.Role;

import java.time.ZonedDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public final class Person {
    private String personId;
    private String username;
    private ZonedDateTime created;
    private Set<Role> roles;
    private Set<Contact> contacts;
}
