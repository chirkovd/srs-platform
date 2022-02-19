package org.systems.dipe.srs.person;

import org.springframework.stereotype.Service;
import org.systems.dipe.srs.person.contacts.Contact;
import org.systems.dipe.srs.person.contacts.ContactClient;
import org.systems.dipe.srs.person.contacts.ContactSearch;

import java.util.Collection;


@Service
public class ContactClientImpl implements ContactClient {

    @Override
    public void create(Collection<Contact> contacts) {

    }

    @Override
    public void update(Collection<Contact> contacts) {

    }

    @Override
    public Collection<Contact> search(ContactSearch search) {
        return null;
    }
}
