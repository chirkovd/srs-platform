package org.systems.dipe.srs.platform.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.systems.dipe.srs.mappers.CommonMapper;
import org.systems.dipe.srs.platform.squad.out.SquadOutDto;
import org.systems.dipe.srs.squad.Squad;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface SquadsDtoMapper extends CommonMapper {
    @Mapping(target = "equipments", ignore = true)
    @Mapping(target = "members", ignore = true)
    SquadOutDto toDto(Squad squad);
}
