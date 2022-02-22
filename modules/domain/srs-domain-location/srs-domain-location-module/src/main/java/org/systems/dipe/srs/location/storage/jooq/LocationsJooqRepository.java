package org.systems.dipe.srs.location.storage.jooq;

import lombok.AllArgsConstructor;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.systems.dipe.srs.location.Location;
import org.systems.dipe.srs.location.LocationsSearch;
import org.systems.dipe.srs.location.storage.LocationsRepository;
import org.systems.dipe.srs.location.storage.mapper.LocationsMapper;

import java.util.Collection;

@Repository
@Transactional
@AllArgsConstructor
public class LocationsJooqRepository implements LocationsRepository {

    private final DefaultDSLContext dsl;
    private final LocationsMapper mapper;

    @Override
    public Location create(Location location) {
        return null;
    }

    @Override
    public Location update(Location location) {
        return null;
    }

    @Override
    public Collection<Location> search(LocationsSearch search) {
        return null;
    }
}
