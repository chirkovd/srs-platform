package org.systems.dipe.srs.person;

import org.systems.dipe.srs.person.contacts.Contact;
import org.systems.dipe.srs.person.roles.Role;

import java.time.ZonedDateTime;
import java.util.Set;

public record Person(String personId, String username, ZonedDateTime created, Set<Role> roles, Set<Contact> contacts) {

}
