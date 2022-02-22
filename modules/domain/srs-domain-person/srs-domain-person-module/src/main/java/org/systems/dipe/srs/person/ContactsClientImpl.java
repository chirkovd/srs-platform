package org.systems.dipe.srs.person;

import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.systems.dipe.srs.person.contacts.Contact;
import org.systems.dipe.srs.person.contacts.ContactsClient;
import org.systems.dipe.srs.person.contacts.ContactsSearch;
import org.systems.dipe.srs.person.storage.ContactsRepository;
import org.systems.dipe.srs.utils.TimeUtils;
import org.systems.dipe.srs.utils.UuidUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;


@Service
@Transactional
@AllArgsConstructor
public class ContactsClientImpl implements ContactsClient {

    private final ContactsRepository repository;

    @Override
    public void create(Collection<Contact> contacts) {
        if (CollectionUtils.isEmpty(contacts)) {
            return;
        }
        for (Contact contact : contacts) {
            if (Objects.isNull(contact.getContactId())) {
                contact.setContactId(UuidUtils.newStr());
            }
            if (Objects.isNull(contact.getCreated())) {
                contact.setCreated(TimeUtils.now());
            }
        }
        repository.create(contacts);
    }

    @Override
    public void update(Collection<Contact> contacts) {
        if (CollectionUtils.isEmpty(contacts)) {
            return;
        }
        repository.update(contacts);
    }

    @Override
    public Collection<Contact> search(ContactsSearch search) {
        if (CollectionUtils.isEmpty(search.getContactIds())
                && CollectionUtils.isEmpty(search.getPersonIds())) {
            return Collections.emptyList();
        }
        return repository.search(search);
    }
}
