package org.systems.dipe.srs.person.contacts;

import java.time.ZonedDateTime;

public record Contact(String contactId, String personId, String phone, String email, ZonedDateTime created) {

}
