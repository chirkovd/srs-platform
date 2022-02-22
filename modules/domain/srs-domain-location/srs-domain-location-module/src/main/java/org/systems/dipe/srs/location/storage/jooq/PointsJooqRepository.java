package org.systems.dipe.srs.location.storage.jooq;

import lombok.AllArgsConstructor;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.systems.dipe.srs.location.Point;
import org.systems.dipe.srs.location.PointsSearch;
import org.systems.dipe.srs.location.storage.PointsRepository;

import java.util.Collection;

@Repository
@Transactional
@AllArgsConstructor
public class PointsJooqRepository implements PointsRepository {

    private final DefaultDSLContext dsl;

    @Override
    public void create(Collection<Point> points) {

    }

    @Override
    public Collection<Point> search(PointsSearch search) {
        return null;
    }
}
