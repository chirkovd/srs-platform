package org.systems.dipe.srs.orchestration.flows.request;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Component;
import org.systems.dipe.srs.orchestration.SrsVariables;
import org.systems.dipe.srs.orchestration.flows.BaseProcess;

@Slf4j
@Component("requestEndProcessor")
public class RequestEndProcessor extends BaseProcess {
    @Override
    public void execution(DelegateExecution execution) throws Exception {
        String requestId = (String) execution.getVariable(SrsVariables.REQUEST_ID);

        log.debug("Request {} was completed", requestId);
    }
}
