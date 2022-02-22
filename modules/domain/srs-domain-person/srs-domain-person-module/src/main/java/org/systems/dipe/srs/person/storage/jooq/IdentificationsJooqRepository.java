package org.systems.dipe.srs.person.storage.jooq;

import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.jooq.Condition;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.systems.dipe.srs.person.identifications.Identification;
import org.systems.dipe.srs.person.identifications.IdentificationsSearch;
import org.systems.dipe.srs.person.jooq.tables.JIdentification;
import org.systems.dipe.srs.person.jooq.tables.records.JIdentificationRecord;
import org.systems.dipe.srs.person.storage.IdentificationsRepository;
import org.systems.dipe.srs.person.storage.mapper.IdentificationsMapper;
import org.systems.dipe.srs.utils.UuidUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
@Transactional
@AllArgsConstructor
public class IdentificationsJooqRepository implements IdentificationsRepository {

    private final DefaultDSLContext dsl;
    private final IdentificationsMapper mapper;

    @Override
    public void create(Collection<Identification> identifications) {
        for (Identification identification : identifications) {
            JIdentificationRecord record = mapper.toJooq(identification);

            dsl.insertInto(JIdentification.IDENTIFICATION)
                    .set(record)
                    .execute();
        }
    }

    @Override
    public void update(Collection<Identification> identifications) {
        for (Identification identification : identifications) {
            JIdentificationRecord record = mapper.toJooq(identification);

            dsl.update(JIdentification.IDENTIFICATION)
                    .set(record)
                    .where(JIdentification.IDENTIFICATION.IDENTITY_ID.eq(record.getIdentityId()),
                            JIdentification.IDENTIFICATION.IDENTITY_TYPE.eq(record.getIdentityType()))
                    .execute();
        }
    }

    @Override
    public Collection<Identification> search(IdentificationsSearch search) {
        List<Condition> conditions = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(search.getPersonIds())) {
            conditions.add(JIdentification.IDENTIFICATION.PERSON_ID.in(UuidUtils.fromStr(search.getPersonIds())));
        }
        if (CollectionUtils.isNotEmpty(search.getValues())) {
            conditions.add(JIdentification.IDENTIFICATION.IDENTITY_ID.in(search.getValues()));
        }
        if (CollectionUtils.isNotEmpty(search.getTypes())) {
            conditions.add(JIdentification.IDENTIFICATION.IDENTITY_TYPE.in(search.getTypes()));
        }
        return dsl.selectFrom(JIdentification.IDENTIFICATION)
                .where(conditions)
                .fetch()
                .map(mapper::fromJooq);
    }
}
