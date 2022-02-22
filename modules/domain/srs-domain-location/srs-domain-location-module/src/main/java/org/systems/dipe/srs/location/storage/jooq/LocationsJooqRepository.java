package org.systems.dipe.srs.location.storage.jooq;

import lombok.AllArgsConstructor;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.systems.dipe.srs.location.Location;
import org.systems.dipe.srs.location.LocationsSearch;
import org.systems.dipe.srs.location.jooq.tables.JLocation;
import org.systems.dipe.srs.location.storage.LocationsRepository;
import org.systems.dipe.srs.location.storage.mapper.LocationsMapper;
import org.systems.dipe.srs.utils.UuidUtils;

import java.util.Collection;

@Repository
@Transactional
@AllArgsConstructor
public class LocationsJooqRepository implements LocationsRepository {

    private final DefaultDSLContext dsl;
    private final LocationsMapper mapper;

    @Override
    public void create(Location location) {
        dsl.insertInto(JLocation.LOCATION)
                .set(mapper.toJooq(location))
                .execute();
    }

    @Override
    public void update(Location location) {
        dsl.update(JLocation.LOCATION)
                .set(mapper.toJooq(location))
                .where(JLocation.LOCATION.LOCATION_ID.eq(UuidUtils.fromStr(location.getLocationId())))
                .execute();
    }

    @Override
    public Collection<Location> search(LocationsSearch search) {
        return dsl.selectFrom(JLocation.LOCATION)
                .where(JLocation.LOCATION.LOCATION_ID.in(UuidUtils.fromStr(search.getLocationIds())))
                .fetch()
                .map(mapper::fromJooq);
    }
}
