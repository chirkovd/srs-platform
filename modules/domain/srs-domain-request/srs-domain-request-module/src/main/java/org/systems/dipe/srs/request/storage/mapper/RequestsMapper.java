package org.systems.dipe.srs.request.storage.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.systems.dipe.srs.mappers.CommonMapper;
import org.systems.dipe.srs.request.Request;
import org.systems.dipe.srs.request.jooq.tables.records.JRequestRecord;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface RequestsMapper extends CommonMapper {

    JRequestRecord toJooq(Request request);

    @Mapping(target = "items", ignore = true)
    @Mapping(target = "locations", ignore = true)
    Request fromJooq(JRequestRecord record);
}
