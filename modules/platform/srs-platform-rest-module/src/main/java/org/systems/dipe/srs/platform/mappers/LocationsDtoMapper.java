package org.systems.dipe.srs.platform.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.systems.dipe.srs.location.Location;
import org.systems.dipe.srs.mappers.CommonMapper;
import org.systems.dipe.srs.platform.locations.in.LocationInDto;
import org.systems.dipe.srs.platform.locations.out.LocationOutDto;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface LocationsDtoMapper extends CommonMapper {

    Location fromInDto(LocationInDto locationInDto);

    LocationOutDto toOutDto(Location location);

}
