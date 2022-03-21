package org.systems.dipe.srs.platform.external.impl;

import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.systems.dipe.srs.location.CommentsClient;
import org.systems.dipe.srs.location.PointsClient;
import org.systems.dipe.srs.platform.external.LocationsFacade;
import org.systems.dipe.srs.platform.external.OrchestrationFacade;
import org.systems.dipe.srs.platform.external.SearchProcessFacade;
import org.systems.dipe.srs.platform.external.SquadFacade;
import org.systems.dipe.srs.platform.locations.in.CommentInDto;
import org.systems.dipe.srs.platform.locations.in.PointInDto;
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

    private final SearchProcessesDtoMapper mapper;

    private final PointsClient pointsClient;
    private final CommentsClient commentsClient;
    private final SearchProcessClient searchProcessClient;

    private final SquadFacade squadFacade;
    private final LocationsFacade locationsFacade;
    private final OrchestrationFacade orchestrationFacade;

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

    @Override
    public void joinSquad(String searchId, String squadId, String volunteerId) {
        SearchProcess searchProcess = getSearchProcess(searchId);
        if (searchProcess.getSquads().stream().noneMatch(s -> s.getSquadId().equals(squadId))) {
            throw new IllegalArgumentException("Wrong squad id");
        }
        squadFacade.joinSquad(squadId, volunteerId);
    }

    @Override
    public void approveSquad(String searchId, String supervisorId) {
        SearchProcess searchProcess = getSearchProcess(searchId);
        if (SearchProcessStatus.IN_PROGRESS != searchProcess.getStatus()) {
            throw new IllegalArgumentException("Wrong search process status");
        }
        orchestrationFacade.assignSquad(searchProcess.getRequestId(), searchId);
    }

    @Override
    public void addPoint(PointInDto point, String searchId, String volunteerId) {
        // TODO add point
    }

    @Override
    public void addComment(CommentInDto comment, String searchId, String pointId, String volunteerId) {
        // TODO add comment
    }

    @Override
    public void completeSearch(String searchId, String supervisorId) {
        SearchProcess searchProcess = getSearchProcess(searchId);
        if (SearchProcessStatus.IN_PROGRESS != searchProcess.getStatus()) {
            throw new IllegalArgumentException("Wrong search process status");
        }
        orchestrationFacade.completeSearchProcess(searchProcess.getRequestId(), searchId);
    }

    private SearchProcess getSearchProcess(String searchId) {
        Collection<SearchProcess> searchProcesses = searchProcessClient.search(
                SearchProcessSearch.builder()
                        .searchIds(Set.of(searchId))
                        .withDetails(true)
                        .build());

        if (1 != CollectionUtils.size(searchProcesses)) {
            throw new IllegalArgumentException("Search process is not found");
        }

        return searchProcesses.iterator().next();
    }
}
