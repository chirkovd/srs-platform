package org.systems.dipe.srs.location.storage.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.systems.dipe.srs.location.Location;
import org.systems.dipe.srs.location.jooq.tables.records.JLocationRecord;
import org.systems.dipe.srs.mappers.CommonMapper;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface LocationsMapper extends CommonMapper {

    JLocationRecord toJooq(Location location);

    @Mapping(target = "points", ignore = true)
    Location fromJooq(JLocationRecord record);
}
