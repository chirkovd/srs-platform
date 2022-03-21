package org.systems.dipe.srs.platform.mappers;

import org.mapstruct.*;
import org.systems.dipe.srs.mappers.CommonMapper;
import org.systems.dipe.srs.platform.requests.in.RequestInDto;
import org.systems.dipe.srs.platform.requests.out.RequestOutDto;
import org.systems.dipe.srs.request.Request;
import org.systems.dipe.srs.request.RequestItem;
import org.systems.dipe.srs.utils.GroupUtils;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface RequestsDtoMapper extends CommonMapper {

    @Mapping(target = "items", ignore = true)
    @Mapping(target = "locations", ignore = true)
    Request fromInDto(RequestInDto requestInDto);

    @Mapping(target = "people", ignore = true)
    @Mapping(target = "itemIds", ignore = true)
    @Mapping(target = "locations", ignore = true)
    RequestOutDto toOutDto(Request request);

    @AfterMapping
    default void afterMapping(@MappingTarget RequestOutDto dto, Request request) {
        dto.setItemIds(GroupUtils.extractUnique(request.getItems(), RequestItem::getItemId));
    }

}
