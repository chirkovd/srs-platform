package org.systems.dipe.srs.orchestration.flows.search;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Component;
import org.systems.dipe.srs.orchestration.OrchestrationClient;
import org.systems.dipe.srs.orchestration.SrsVariables;
import org.systems.dipe.srs.orchestration.external.SearchProcessFacade;
import org.systems.dipe.srs.orchestration.flows.BaseProcess;
import org.systems.dipe.srs.search.SearchProcessStatus;

@Slf4j
@AllArgsConstructor
@Component("searchCancelProcessor")
public class SearchCancelProcessor extends BaseProcess {

    private final SearchProcessFacade searchProcessFacade;
    private final OrchestrationClient orchestrationClient;

    @Override
    public void execution(DelegateExecution execution) throws Exception {
        String requestId = (String) execution.getVariable(SrsVariables.REQUEST_ID);
        String searchId = (String) execution.getVariable(SrsVariables.SEARCH_ID);

        // update state
        searchProcessFacade.updateStatus(searchId, SearchProcessStatus.CANCELLED);

        log.debug("Search process {} is cancelled, cancel request {}", searchId, requestId);
        orchestrationClient.cancelRequest(requestId);
    }
}
