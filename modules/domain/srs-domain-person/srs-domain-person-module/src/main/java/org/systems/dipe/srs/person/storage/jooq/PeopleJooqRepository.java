package org.systems.dipe.srs.person.storage.jooq;

import lombok.AllArgsConstructor;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.systems.dipe.srs.person.PeopleSearch;
import org.systems.dipe.srs.person.Person;
import org.systems.dipe.srs.person.jooq.tables.JPerson;
import org.systems.dipe.srs.person.jooq.tables.records.JPersonRecord;
import org.systems.dipe.srs.person.storage.PeopleRepository;
import org.systems.dipe.srs.person.storage.mapper.PeopleMapper;
import org.systems.dipe.srs.utils.UuidUtils;

import java.util.Collection;

@Repository
@Transactional
@AllArgsConstructor
public class PeopleJooqRepository implements PeopleRepository {

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
                .where(JPerson.PERSON.PERSON_ID.eq(record.getPersonId()))
                .execute();
    }

    @Override
    public Collection<Person> find(PeopleSearch search) {
        return dsl.selectFrom(JPerson.PERSON)
                .where(JPerson.PERSON.PERSON_ID.in(UuidUtils.fromStr(search.getPersonIds())))
                .fetch()
                .map(mapper::fromJooq);
    }
}
