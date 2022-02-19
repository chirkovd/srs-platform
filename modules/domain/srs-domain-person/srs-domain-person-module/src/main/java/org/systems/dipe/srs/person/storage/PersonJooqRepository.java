package org.systems.dipe.srs.person.storage;

import lombok.AllArgsConstructor;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.systems.dipe.srs.person.Person;
import org.systems.dipe.srs.person.jooq.tables.JPerson;
import org.systems.dipe.srs.person.jooq.tables.records.JPersonRecord;
import org.systems.dipe.srs.person.storage.mapper.PeopleMapper;

import java.util.Objects;
import java.util.UUID;

@Repository
@Transactional
@AllArgsConstructor
public class PersonJooqRepository implements PersonRepository {

    private final DefaultDSLContext dsl;
    private final PeopleMapper mapper;

    @Override
    public Person create(Person person) {
        JPersonRecord record = mapper.toJooq(person);

        if (Objects.isNull(record.getPersonId())) {
            record.setPersonId(UUID.randomUUID());
        }

        dsl.insertInto(JPerson.PERSON)
                .set(record)
                .onDuplicateKeyUpdate()
                .set(record)
                .execute();

        return person;
    }
}
