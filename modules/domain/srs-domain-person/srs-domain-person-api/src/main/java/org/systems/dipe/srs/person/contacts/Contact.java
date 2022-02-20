package org.systems.dipe.srs.person.contacts;

import lombok.*;

import java.time.ZonedDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public final class Contact {
    private String contactId;
    private String personId;
    private String phone;
    private String email;
    private ZonedDateTime created;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return Objects.equals(contactId, contact.contactId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contactId);
    }
}
