package org.systems.dipe.srs.request.storage.jooq;

import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.jooq.Condition;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.systems.dipe.srs.request.RequestLocation;
import org.systems.dipe.srs.request.RequestLocationsSearch;
import org.systems.dipe.srs.request.storage.RequestLocationsRepository;
import org.systems.dipe.srs.request.storage.mapper.RequestLocationsMapper;
import org.systems.dipe.srs.utils.GroupUtils;
import org.systems.dipe.srs.utils.UuidUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import static org.systems.dipe.srs.request.jooq.tables.JRequestLocation.REQUEST_LOCATION;

@Repository
@Transactional
@AllArgsConstructor
public class RequestLocationsJooqRepository implements RequestLocationsRepository {

    private final DefaultDSLContext dsl;
    private final RequestLocationsMapper mapper;

    @Override
    public void create(Collection<RequestLocation> locations) {
        dsl.batchInsert(mapper.toJooq(locations)).execute();
    }

    @Override
    public void update(Collection<RequestLocation> locations) {
        // clean up previous locations
        Set<String> requestIds = GroupUtils.extractUnique(locations, RequestLocation::getRequestId);
        dsl.deleteFrom(REQUEST_LOCATION)
                .where(REQUEST_LOCATION.REQUEST_ID.in(UuidUtils.fromStr(requestIds)))
                .execute();

        create(locations);
    }

    @Override
    public Collection<RequestLocation> search(RequestLocationsSearch search) {
        Collection<Condition> conditions = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(search.getLocationIds())) {
            conditions.add(REQUEST_LOCATION.LOCATION_ID.in(UuidUtils.fromStr(search.getLocationIds())));
        }
        if (CollectionUtils.isNotEmpty(search.getRequestsIds())) {
            conditions.add(REQUEST_LOCATION.REQUEST_ID.in(UuidUtils.fromStr(search.getRequestsIds())));
        }
        return dsl.selectFrom(REQUEST_LOCATION)
                .where(conditions)
                .fetch()
                .map(mapper::fromJooq);
    }
}
