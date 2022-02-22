package org.systems.dipe.srs.location.storage.jooq;

import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.jooq.Condition;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.systems.dipe.srs.location.Point;
import org.systems.dipe.srs.location.PointsSearch;
import org.systems.dipe.srs.location.jooq.tables.JPoint;
import org.systems.dipe.srs.location.jooq.tables.records.JPointRecord;
import org.systems.dipe.srs.location.storage.PointsRepository;
import org.systems.dipe.srs.location.storage.mapper.PointsMapper;
import org.systems.dipe.srs.utils.UuidUtils;

import java.util.ArrayList;
import java.util.Collection;

@Repository
@Transactional
@AllArgsConstructor
public class PointsJooqRepository implements PointsRepository {

    private final DefaultDSLContext dsl;
    private final PointsMapper mapper;

    @Override
    public void create(Collection<Point> points) {
        dsl.batchInsert(mapper.toJooq(points)).execute();
    }

    @Override
    public void update(Collection<Point> points) {
        for (Point point : points) {
            JPointRecord record = mapper.toJooq(point);
            dsl.insertInto(JPoint.POINT)
                    .set(record)
                    .onDuplicateKeyUpdate()
                    .set(record)
                    .execute();
        }
    }

    @Override
    public Collection<Point> search(PointsSearch search) {
        Collection<Condition> conditions = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(search.getLocationIds())) {
            conditions.add(JPoint.POINT.LOCATION_ID.in(UuidUtils.fromStr(search.getLocationIds())));
        }
        if (CollectionUtils.isNotEmpty(search.getPointIds())) {
            conditions.add(JPoint.POINT.POINT_ID.in(UuidUtils.fromStr(search.getPointIds())));
        }

        return dsl.selectFrom(JPoint.POINT)
                .where(conditions)
                .fetch()
                .map(mapper::fromJooq);
    }
}
