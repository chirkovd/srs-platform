package org.systems.dipe.srs.platform.external.impl;

import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
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
import org.systems.dipe.srs.request.*;
import org.systems.dipe.srs.utils.GroupUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Transactional
@AllArgsConstructor
@ConditionalOnBean(RequestsClient.class)
public class RequestFacadeImpl implements RequestFacade {

    private final RequestsClient client;
    private final RequestsDtoMapper mapper;

    private final PeopleFacade peopleFacade;
    private final LocationsFacade locationsFacade;
    private final OrchestrationFacade orchestrationFacade;

    @Override
    public RequestOutDto submitRequest(RequestInDto requestInDto) {
        List<LocationOutDto> locations = new ArrayList<>();
        for (LocationInDto location : requestInDto.getLocations()) {
            locations.add(locationsFacade.create(location));
        }

        List<PersonOutDto> people = new ArrayList<>();
        for (PersonInDto person : requestInDto.getPeople()) {
            // TODO check existing
            people.add(peopleFacade.create(person));
        }

        Request request = mapper.fromInDto(requestInDto);
        request.setLocations(locations.stream().map(dto -> {
            RequestLocation location = new RequestLocation();
            location.setLocationId(dto.getLocationId());
            return location;
        }).collect(Collectors.toList()));
        request.setItems(people.stream().map(p -> {
            RequestItem requestItem = new RequestItem();
            requestItem.setTargetId(p.getPersonId());
            return requestItem;
        }).collect(Collectors.toList()));

        RequestOutDto out = mapper.toOutDto(client.create(request));
        out.setLocations(locations);
        out.setPeople(people);

        orchestrationFacade.submitRequest(out.getRequestId());

        return out;
    }

    @Override
    public RequestOutDto findRequest(String requestId) {
        Collection<Request> requests = client.search(RequestsSearch.builder()
                .requestIds(Set.of(requestId))
                .withDetails(true)
                .build());
        if (CollectionUtils.isEmpty(requests)) {
            throw new IllegalArgumentException("Cannot find request by id " + requestId);
        }
        Request request = requests.iterator().next();
        RequestOutDto outDto = mapper.toOutDto(request);
        outDto.setLocations(locationsFacade.search(
                GroupUtils.extractUnique(
                        request.getLocations(),
                        RequestLocation::getLocationId
                )
        ));
        outDto.setPeople(peopleFacade.search(
                GroupUtils.extractUnique(
                        request.getItems(),
                        RequestItem::getTargetId
                )
        ));

        return outDto;
    }

    @Override
    public void assign(String requestId, String supervisorId) {
        orchestrationFacade.assignRequest(requestId, supervisorId);
    }

    @Override
    public void approve(String requestId, String supervisorId) {
        orchestrationFacade.approveRequest(requestId, supervisorId);
    }
}
