package org.systems.dipe.srs.squad.storage.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.systems.dipe.srs.mappers.CommonMapper;
import org.systems.dipe.srs.squad.Squad;
import org.systems.dipe.srs.squad.jooq.tables.records.JSquadRecord;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface SquadsMapper extends CommonMapper {

    JSquadRecord toJooq(Squad squad);

    @Mapping(target = "members", ignore = true)
    @Mapping(target = "equipments", ignore = true)
    Squad fromJooq(JSquadRecord record);
}
