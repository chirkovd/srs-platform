package org.systems.dipe.srs.location.storage.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.systems.dipe.srs.location.Point;
import org.systems.dipe.srs.location.jooq.tables.records.JPointRecord;
import org.systems.dipe.srs.mappers.CommonMapper;

import java.util.Collection;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface PointsMapper extends CommonMapper {

    JPointRecord toJooq(Point point);

    Collection<JPointRecord> toJooq(Collection<Point> points);

    @Mapping(target = "comments", ignore = true)
    Point fromJooq(JPointRecord record);

}
