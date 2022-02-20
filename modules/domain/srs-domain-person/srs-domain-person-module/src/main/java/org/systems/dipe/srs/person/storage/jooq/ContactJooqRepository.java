package org.systems.dipe.srs.person.storage.jooq;

import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.jooq.Condition;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.systems.dipe.srs.person.contacts.Contact;
import org.systems.dipe.srs.person.contacts.ContactSearch;
import org.systems.dipe.srs.person.jooq.tables.JContact;
import org.systems.dipe.srs.person.jooq.tables.records.JContactRecord;
import org.systems.dipe.srs.person.storage.ContactRepository;
import org.systems.dipe.srs.person.storage.mapper.ContactsMapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@Transactional
@AllArgsConstructor
public class ContactJooqRepository implements ContactRepository {

    private final DefaultDSLContext dsl;
    private final ContactsMapper mapper;

    @Override
    public void create(Collection<Contact> contacts) {
        for (Contact contact : contacts) {
            JContactRecord record = mapper.toJooq(contact);

            dsl.insertInto(JContact.CONTACT)
                    .set(record)
                    .execute();
        }
    }

    @Override
    public void update(Collection<Contact> contacts) {
        for (Contact contact : contacts) {
            JContactRecord record = mapper.toJooq(contact);

            dsl.update(JContact.CONTACT)
                    .set(record)
                    .where(JContact.CONTACT.CONTACT_ID.eq(record.getContactId()))
                    .execute();
        }
    }

    @Override
    public Collection<Contact> search(ContactSearch search) {
        List<Condition> conditions = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(search.getPersonIds())) {
            conditions.add(JContact.CONTACT.PERSON_ID.in(
                    search.getPersonIds().stream().map(UUID::fromString).collect(Collectors.toSet())
            ));
        }
        if (CollectionUtils.isNotEmpty(search.getContactIds())) {
            conditions.add(JContact.CONTACT.CONTACT_ID.in(
                    search.getContactIds().stream().map(UUID::fromString).collect(Collectors.toSet())
            ));
        }
        return dsl.selectFrom(JContact.CONTACT)
                .where(conditions)
                .fetch()
                .map(mapper::fromJooq);
    }
}
