package org.systems.dipe.srs.platform.external.impl;

import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.systems.dipe.srs.location.Location;
import org.systems.dipe.srs.location.LocationsClient;
import org.systems.dipe.srs.location.LocationsSearch;
import org.systems.dipe.srs.platform.external.LocationsFacade;
import org.systems.dipe.srs.platform.locations.in.LocationInDto;
import org.systems.dipe.srs.platform.locations.out.LocationOutDto;
import org.systems.dipe.srs.platform.mappers.LocationsDtoMapper;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Transactional
@AllArgsConstructor
@Component("platformLocationsFacade")
@ConditionalOnBean(LocationsClient.class)
public class LocationsFacadeImpl implements LocationsFacade {

    private final LocationsClient client;
    private final LocationsDtoMapper mapper;

    @Override
    public LocationOutDto create(LocationInDto location) {
        return mapper.toOutDto(client.create(mapper.fromInDto(location)));
    }

    @Override
    public List<LocationOutDto> search(Set<String> locationIds) {
        Collection<Location> locations = client.search(LocationsSearch.builder()
                .locationIds(locationIds)
                .withDetails(true)
                .build());

        return locations.stream()
                .map(mapper::toOutDto)
                .collect(Collectors.toList());
    }
}
