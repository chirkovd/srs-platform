package org.systems.dipe.srs.person.contacts;

import java.util.Collection;

public interface ContactClient {

    Contact create(Contact contact);

    Contact update(Contact contact);

    Collection<Contact> find(ContactSearch search);
}
