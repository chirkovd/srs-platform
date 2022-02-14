package org.systems.dipe.srs.person;

import org.springframework.stereotype.Service;
import org.systems.dipe.srs.person.contacts.Contact;
import org.systems.dipe.srs.person.contacts.ContactClient;
import org.systems.dipe.srs.person.contacts.ContactSearch;

import java.util.Collection;


@Service
public class ContactClientImpl implements ContactClient {

    @Override
    public Contact create(Contact contact) {
        return null;
    }

    @Override
    public Contact update(Contact contact) {
        return null;
    }

    @Override
    public Collection<Contact> find(ContactSearch search) {
        return null;
    }
}
