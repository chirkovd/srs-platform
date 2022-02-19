package org.systems.dipe.srs.person.contacts;

import java.util.Collection;

public interface ContactClient {

    void create(Collection<Contact> contacts);

    void update(Collection<Contact> contacts);

    Collection<Contact> search(ContactSearch search);
}
