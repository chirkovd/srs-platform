package org.systems.dipe.srs.platform.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.systems.dipe.srs.mappers.CommonMapper;
import org.systems.dipe.srs.platform.search.out.SearchProcessOutDto;
import org.systems.dipe.srs.search.SearchProcess;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface SearchProcessesDtoMapper extends CommonMapper {

    @Mapping(target = "locations", ignore = true)
    @Mapping(target = "squads", ignore = true)
    SearchProcessOutDto toDto(SearchProcess process);

}
