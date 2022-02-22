package org.systems.dipe.srs.person.storage.jooq;

import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.jooq.Condition;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.systems.dipe.srs.person.contacts.Contact;
import org.systems.dipe.srs.person.contacts.ContactsSearch;
import org.systems.dipe.srs.person.jooq.tables.JContact;
import org.systems.dipe.srs.person.jooq.tables.records.JContactRecord;
import org.systems.dipe.srs.person.storage.ContactsRepository;
import org.systems.dipe.srs.person.storage.mapper.ContactsMapper;
import org.systems.dipe.srs.utils.UuidUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
@Transactional
@AllArgsConstructor
public class ContactsJooqRepository implements ContactsRepository {

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
    public Collection<Contact> search(ContactsSearch search) {
        List<Condition> conditions = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(search.getPersonIds())) {
            conditions.add(JContact.CONTACT.PERSON_ID.in(UuidUtils.fromStr(search.getPersonIds())));
        }
        if (CollectionUtils.isNotEmpty(search.getContactIds())) {
            conditions.add(JContact.CONTACT.CONTACT_ID.in(UuidUtils.fromStr(search.getContactIds())));
        }
        return dsl.selectFrom(JContact.CONTACT)
                .where(conditions)
                .fetch()
                .map(mapper::fromJooq);
    }
}
