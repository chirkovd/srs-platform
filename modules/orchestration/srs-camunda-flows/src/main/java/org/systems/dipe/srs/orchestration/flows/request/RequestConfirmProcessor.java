package org.systems.dipe.srs.orchestration.flows.request;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Component;
import org.systems.dipe.srs.orchestration.OrchestrationClient;
import org.systems.dipe.srs.orchestration.SrsVariables;
import org.systems.dipe.srs.orchestration.external.RequestsFacade;
import org.systems.dipe.srs.orchestration.external.SearchProcessFacade;
import org.systems.dipe.srs.orchestration.flows.BaseProcess;
import org.systems.dipe.srs.search.SearchProcess;
import org.systems.dipe.srs.search.SearchProcessStatus;

@Slf4j
@AllArgsConstructor
@Component("requestConfirmProcessor")
public class RequestConfirmProcessor extends BaseProcess {

    private final RequestsFacade requestsFacade;
    private final SearchProcessFacade searchProcessFacade;
    private final OrchestrationClient orchestrationClient;

    @Override
    public void execution(DelegateExecution execution) throws Exception {
        String requestId = (String) execution.getVariable(SrsVariables.REQUEST_ID);
        String supervisorId = (String) execution.getVariable(SrsVariables.SUPERVISOR_ID);

        log.debug("Request {} is approved by supervisor {}", requestId, supervisorId);
        requestsFacade.approve(requestId, supervisorId);

        SearchProcess searchProcess = new SearchProcess();
        searchProcess.setRequestId(requestId);
        searchProcess.setStatus(SearchProcessStatus.CREATED);

        log.debug("Search process is created for request {}", requestId);
        searchProcess = searchProcessFacade.create(searchProcess);

        orchestrationClient.submitSearchProcess(
                searchProcess.getRequestId(),
                searchProcess.getSearchId()
        );
    }
}
