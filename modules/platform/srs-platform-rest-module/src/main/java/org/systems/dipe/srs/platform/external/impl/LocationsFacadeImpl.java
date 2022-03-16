package org.systems.dipe.srs.platform.external.impl;

import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;
import org.systems.dipe.srs.location.LocationsClient;
import org.systems.dipe.srs.platform.external.LocationsFacade;
import org.systems.dipe.srs.platform.locations.in.LocationInDto;
import org.systems.dipe.srs.platform.locations.out.LocationOutDto;
import org.systems.dipe.srs.platform.mappers.LocationsDtoMapper;

@Component
@AllArgsConstructor
@ConditionalOnBean(LocationsClient.class)
public class LocationsFacadeImpl implements LocationsFacade {

    private final LocationsClient client;
    private final LocationsDtoMapper mapper;

    @Override
    public LocationOutDto create(LocationInDto location) {
        return mapper.toOutDto(client.create(mapper.fromInDto(location)));
    }
}
