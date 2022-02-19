package org.systems.dipe.srs.location.storage;

import lombok.AllArgsConstructor;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.systems.dipe.srs.location.Comment;
import org.systems.dipe.srs.location.Location;
import org.systems.dipe.srs.location.LocationSearch;
import org.systems.dipe.srs.location.Point;

import java.util.Collection;

@Repository
@Transactional
@AllArgsConstructor
public class LocationJooqRepository implements LocationRepository {

    private final DefaultDSLContext dsl;

    @Override
    public Location create(Location location) {
        return null;
    }

    @Override
    public Location update(Location location) {
        return null;
    }

    @Override
    public Collection<Location> search(LocationSearch search) {
        return null;
    }

    @Override
    public void addPoints(Collection<Point> points) {

    }

    @Override
    public void addComments(Collection<Comment> comments) {

    }
}
