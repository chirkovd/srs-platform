package org.systems.dipe.srs.person.storage;

import org.systems.dipe.srs.person.contacts.Contact;
import org.systems.dipe.srs.person.contacts.ContactsSearch;

import java.util.Collection;

public interface ContactsRepository {

    void create(Collection<Contact> contacts);

    void update(Collection<Contact> contacts);

    Collection<Contact> search(ContactsSearch search);

}
