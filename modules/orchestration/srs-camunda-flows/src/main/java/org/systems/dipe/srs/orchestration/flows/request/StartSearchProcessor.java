package org.systems.dipe.srs.orchestration.flows.request;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Component;
import org.systems.dipe.srs.orchestration.SrsVariables;
import org.systems.dipe.srs.orchestration.external.RequestsFacade;
import org.systems.dipe.srs.orchestration.flows.BaseProcess;

@Slf4j
@AllArgsConstructor
@Component("startSearchProcessor")
public class StartSearchProcessor extends BaseProcess {

    private final RequestsFacade requestsFacade;

    @Override
    public void execution(DelegateExecution execution) throws Exception {
        String requestId = (String) execution.getVariable(SrsVariables.REQUEST_ID);

        log.debug("Request {} is approved, start search process", requestId);
        requestsFacade.approve(requestId);

        // TODO start search process
    }
}
