package org.systems.dipe.srs.person.contacts;

import java.util.Collection;

public interface ContactsClient {

    void create(Collection<Contact> contacts);

    void update(Collection<Contact> contacts);

    Collection<Contact> search(ContactsSearch search);
}
