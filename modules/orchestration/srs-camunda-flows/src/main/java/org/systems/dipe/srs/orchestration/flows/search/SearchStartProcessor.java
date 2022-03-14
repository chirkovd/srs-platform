package org.systems.dipe.srs.orchestration.flows.search;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Component;
import org.systems.dipe.srs.orchestration.SrsVariables;
import org.systems.dipe.srs.orchestration.external.RequestsFacade;
import org.systems.dipe.srs.orchestration.external.SearchProcessFacade;
import org.systems.dipe.srs.orchestration.flows.BaseProcess;
import org.systems.dipe.srs.request.Request;
import org.systems.dipe.srs.search.SearchProcess;
import org.systems.dipe.srs.search.SearchProcessStatus;
import org.systems.dipe.srs.search.SearchSquad;

import java.util.List;
import java.util.Objects;

@Slf4j
@AllArgsConstructor
@Component("searchStartProcessor")
public class SearchStartProcessor extends BaseProcess {

    private final RequestsFacade requestsFacade;
    private final SearchProcessFacade searchProcessFacade;

    @Override
    public void execution(DelegateExecution execution) throws Exception {
        String requestId = (String) execution.getVariable(SrsVariables.REQUEST_ID);
        String searchId = (String) execution.getVariable(SrsVariables.SEARCH_ID);

        log.debug("Full-fill search process {} by request {}", searchId, requestId);
        Request request = Objects.requireNonNull(requestsFacade.find(requestId));
        SearchProcess searchProcess = Objects.requireNonNull(searchProcessFacade.find(searchId));

        // TODO add location and
        SearchSquad searchSquad = new SearchSquad();
        searchProcess.setSquads(List.of(searchSquad));
        searchProcess.setStatus(SearchProcessStatus.IN_PROGRESS);
        searchProcessFacade.update(searchProcess);
    }
}
