package org.systems.dipe.srs.platform.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.systems.dipe.srs.location.Comment;
import org.systems.dipe.srs.location.Location;
import org.systems.dipe.srs.location.Point;
import org.systems.dipe.srs.mappers.CommonMapper;
import org.systems.dipe.srs.platform.locations.in.CommentInDto;
import org.systems.dipe.srs.platform.locations.in.LocationInDto;
import org.systems.dipe.srs.platform.locations.in.PointInDto;
import org.systems.dipe.srs.platform.locations.out.LocationOutDto;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface LocationsDtoMapper extends CommonMapper {

    Location fromInDto(LocationInDto locationInDto);

    @Mapping(target = "locationId", ignore = true)
    Point fromIdDto(PointInDto pointInDto);

    @Mapping(target = "pointId", ignore = true)
    Comment fromInDto(CommentInDto commentInDto);

    LocationOutDto toOutDto(Location location);

}
