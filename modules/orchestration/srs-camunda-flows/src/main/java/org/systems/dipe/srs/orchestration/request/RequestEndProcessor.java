package org.systems.dipe.srs.orchestration.request;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Component;
import org.systems.dipe.srs.orchestration.BaseProcess;

@Component("requestEndProcessor")
public class RequestEndProcessor extends BaseProcess {
    @Override
    public void execution(DelegateExecution execution) throws Exception {

    }
}
