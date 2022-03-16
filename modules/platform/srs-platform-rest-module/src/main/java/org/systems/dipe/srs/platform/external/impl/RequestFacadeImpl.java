package org.systems.dipe.srs.platform.external.impl;

import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;
import org.systems.dipe.srs.platform.external.LocationsFacade;
import org.systems.dipe.srs.platform.external.OrchestrationFacade;
import org.systems.dipe.srs.platform.external.PeopleFacade;
import org.systems.dipe.srs.platform.external.RequestFacade;
import org.systems.dipe.srs.platform.locations.in.LocationInDto;
import org.systems.dipe.srs.platform.locations.out.LocationOutDto;
import org.systems.dipe.srs.platform.mappers.RequestsDtoMapper;
import org.systems.dipe.srs.platform.people.in.PersonInDto;
import org.systems.dipe.srs.platform.people.out.PersonOutDto;
import org.systems.dipe.srs.platform.requests.in.RequestInDto;
import org.systems.dipe.srs.platform.requests.out.RequestOutDto;
import org.systems.dipe.srs.request.RequestsClient;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
@ConditionalOnBean(RequestsClient.class)
public class RequestFacadeImpl implements RequestFacade {

    private final RequestsClient client;
    private final RequestsDtoMapper mapper;

    private final PeopleFacade peopleFacade;
    private final LocationsFacade locationsFacade;
    private final OrchestrationFacade orchestrationFacade;

    @Override
    public RequestOutDto submitRequest(RequestInDto request) {
        List<LocationOutDto> locations = new ArrayList<>();
        for (LocationInDto location : request.getLocations()) {
            locations.add(locationsFacade.create(location));
        }

        List<PersonOutDto> people = new ArrayList<>();
        for (PersonInDto person : request.getTargetPeople()) {
            // TODO check existing
            people.add(peopleFacade.create(person));
        }

        RequestOutDto out = mapper.toOutDto(client.create(mapper.fromInDto(request)));
        out.setLocations(locations);
        out.setTargetPeople(people);

        orchestrationFacade.submitRequest(out.getRequestId());

        return out;
    }
}
