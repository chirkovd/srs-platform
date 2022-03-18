package org.systems.dipe.srs.platform.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.systems.dipe.srs.mappers.CommonMapper;
import org.systems.dipe.srs.platform.requests.in.RequestInDto;
import org.systems.dipe.srs.platform.requests.out.RequestOutDto;
import org.systems.dipe.srs.request.Request;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface RequestsDtoMapper extends CommonMapper {

    @Mapping(target = "items", ignore = true)
    @Mapping(target = "locations", ignore = true)
    Request fromInDto(RequestInDto requestInDto);

    @Mapping(target = "people", ignore = true)
    @Mapping(target = "locations", ignore = true)
    RequestOutDto toOutDto(Request request);

}
