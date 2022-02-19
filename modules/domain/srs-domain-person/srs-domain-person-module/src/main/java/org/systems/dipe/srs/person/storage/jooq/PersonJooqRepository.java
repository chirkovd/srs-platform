package org.systems.dipe.srs.person.storage.jooq;

import lombok.AllArgsConstructor;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.systems.dipe.srs.person.Person;
import org.systems.dipe.srs.person.storage.PersonRepository;
import org.systems.dipe.srs.person.storage.jooq.tables.JPerson;
import org.systems.dipe.srs.person.storage.jooq.tables.records.JPersonRecord;

@Repository
@Transactional
@AllArgsConstructor
public class PersonJooqRepository implements PersonRepository {

    private final DefaultDSLContext dsl;

    @Override
    public Person create(Person person) {
        JPersonRecord record = new JPersonRecord();

        dsl.insertInto(JPerson.PERSON)
                .set(record)
                .onDuplicateKeyUpdate()
                .set(record)
                .execute();

        return person;
    }
}
