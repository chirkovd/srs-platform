package org.systems.dipe.srs.person.storage.jooq;

import lombok.AllArgsConstructor;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.systems.dipe.srs.person.Person;
import org.systems.dipe.srs.person.PersonSearch;
import org.systems.dipe.srs.person.jooq.tables.JPerson;
import org.systems.dipe.srs.person.jooq.tables.records.JPersonRecord;
import org.systems.dipe.srs.person.storage.PersonRepository;
import org.systems.dipe.srs.person.storage.mapper.PeopleMapper;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@Transactional
@AllArgsConstructor
public class PersonJooqRepository implements PersonRepository {

    private final DefaultDSLContext dsl;
    private final PeopleMapper mapper;

    @Override
    public void create(Person person) {
        JPersonRecord record = mapper.toJooq(person);

        dsl.insertInto(JPerson.PERSON)
                .set(record)
                .execute();
    }

    @Override
    public void update(Person person) {
        JPersonRecord record = mapper.toJooq(person);

        dsl.update(JPerson.PERSON)
                .set(record)
                .execute();
    }

    @Override
    public Collection<Person> find(PersonSearch search) {
        return dsl.selectFrom(JPerson.PERSON)
                .where(JPerson.PERSON.PERSON_ID.in(
                        search.getPersonIds().stream()
                                .map(UUID::fromString)
                                .collect(Collectors.toSet()))
                )
                .fetch()
                .map(mapper::fromJooq);
    }
}
