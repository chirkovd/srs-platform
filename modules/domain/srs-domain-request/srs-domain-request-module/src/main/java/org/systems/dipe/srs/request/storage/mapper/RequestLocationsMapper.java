package org.systems.dipe.srs.request.storage.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.systems.dipe.srs.mappers.CommonMapper;
import org.systems.dipe.srs.request.RequestLocation;
import org.systems.dipe.srs.request.jooq.tables.records.JRequestLocationRecord;

import java.util.Collection;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface RequestLocationsMapper extends CommonMapper {

    JRequestLocationRecord toJooq(RequestLocation location);

    Collection<JRequestLocationRecord> toJooq(Collection<RequestLocation> locations);

    RequestLocation fromJooq(JRequestLocationRecord record);
}
