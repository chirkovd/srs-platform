package org.systems.dipe.srs.platform.external.impl;

import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.systems.dipe.srs.platform.external.LocationsFacade;
import org.systems.dipe.srs.platform.external.SearchProcessFacade;
import org.systems.dipe.srs.platform.external.SquadFacade;
import org.systems.dipe.srs.platform.mappers.SearchProcessesDtoMapper;
import org.systems.dipe.srs.platform.search.SearchProcessRequest;
import org.systems.dipe.srs.platform.search.out.SearchProcessOutDto;
import org.systems.dipe.srs.search.*;
import org.systems.dipe.srs.utils.GroupUtils;

import java.util.Collection;
import java.util.Set;

@Transactional
@AllArgsConstructor
@Component("platformSearchProcessFacade")
@ConditionalOnBean(SearchProcessClient.class)
public class SearchProcessFacadeImpl implements SearchProcessFacade {

    private final SearchProcessClient searchProcessClient;
    private final SearchProcessesDtoMapper mapper;

    private final LocationsFacade locationsFacade;
    private final SquadFacade squadFacade;

    @Override
    public SearchProcessOutDto search(SearchProcessRequest request) {
        Collection<SearchProcess> searchProcesses = searchProcessClient.search(
                SearchProcessSearch.builder()
                        .requestIds(Set.of(request.getRequestId()))
                        .withDetails(true)
                        .build());

        if (1 != CollectionUtils.size(searchProcesses)) {
            throw new IllegalArgumentException("Search process is not defined");
        }

        SearchProcess searchProcess = searchProcesses.iterator().next();
        SearchProcessOutDto dto = mapper.toDto(searchProcess);

        Set<String> locationIds = GroupUtils.extractUnique(searchProcess.getLocations(), SearchLocation::getLocationId);
        dto.setLocations(locationsFacade.search(locationIds));

        Set<String> squadIds = GroupUtils.extractUnique(searchProcess.getSquads(), SearchSquad::getSquadId);
        dto.setSquads(squadFacade.search(squadIds));

        return dto;
    }
}
