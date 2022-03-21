package org.systems.dipe.srs.orchestration.flows.search;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Component;
import org.systems.dipe.srs.orchestration.SrsVariables;
import org.systems.dipe.srs.orchestration.external.RequestsFacade;
import org.systems.dipe.srs.orchestration.external.SearchProcessFacade;
import org.systems.dipe.srs.orchestration.external.SquadsFacade;
import org.systems.dipe.srs.orchestration.flows.BaseProcess;
import org.systems.dipe.srs.request.Request;
import org.systems.dipe.srs.search.SearchLocation;
import org.systems.dipe.srs.search.SearchProcess;
import org.systems.dipe.srs.search.SearchProcessStatus;
import org.systems.dipe.srs.search.SearchSquad;
import org.systems.dipe.srs.squad.Squad;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Component("searchStartProcessor")
public class SearchStartProcessor extends BaseProcess {

    private final SquadsFacade squadsFacade;
    private final RequestsFacade requestsFacade;
    private final SearchProcessFacade searchProcessFacade;

    @Override
    public void execution(DelegateExecution execution) throws Exception {
        String requestId = (String) execution.getVariable(SrsVariables.REQUEST_ID);
        String searchId = (String) execution.getVariable(SrsVariables.SEARCH_ID);

        log.debug("Full-fill search process {} by request {}", searchId, requestId);
        Request request = Objects.requireNonNull(requestsFacade.find(requestId));
        SearchProcess searchProcess = Objects.requireNonNull(searchProcessFacade.find(searchId));

        Squad squad = squadsFacade.create(new Squad());

        SearchSquad searchSquad = new SearchSquad();
        searchSquad.setSquadId(squad.getSquadId());

        searchProcess.setSquads(List.of(searchSquad));
        searchProcess.setLocations(request.getLocations().stream()
                .map(location -> {
                    SearchLocation sl = new SearchLocation();
                    sl.setLocationId(location.getLocationId());
                    return sl;
                })
                .collect(Collectors.toList()));
        searchProcess.setStatus(SearchProcessStatus.IN_PROGRESS);

        searchProcessFacade.update(searchProcess);
    }
}
