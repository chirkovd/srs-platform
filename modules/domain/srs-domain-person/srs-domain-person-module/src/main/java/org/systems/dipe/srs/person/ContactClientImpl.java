package org.systems.dipe.srs.person;

import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.systems.dipe.srs.person.contacts.Contact;
import org.systems.dipe.srs.person.contacts.ContactClient;
import org.systems.dipe.srs.person.contacts.ContactSearch;
import org.systems.dipe.srs.person.storage.ContactRepository;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.UUID;


@Service
@Transactional
@AllArgsConstructor
public class ContactClientImpl implements ContactClient {

    private final ContactRepository repository;

    @Override
    public void create(Collection<Contact> contacts) {
        if (CollectionUtils.isEmpty(contacts)) {
            return;
        }
        for (Contact contact : contacts) {
            if (Objects.isNull(contact.getContactId())) {
                contact.setContactId(UUID.randomUUID().toString());
            }
            if (Objects.isNull(contact.getCreated())) {
                contact.setCreated(ZonedDateTime.now(ZoneId.of("UTC")));
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
    public Collection<Contact> search(ContactSearch search) {
        if (CollectionUtils.isEmpty(search.getContactIds())
                && CollectionUtils.isEmpty(search.getPersonIds())) {
            return Collections.emptyList();
        }
        return repository.search(search);
    }
}
