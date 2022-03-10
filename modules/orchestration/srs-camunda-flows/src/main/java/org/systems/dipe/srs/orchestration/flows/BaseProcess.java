package org.systems.dipe.srs.orchestration.flows;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class BaseProcess implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        execution(execution);
    }

    public abstract void execution(DelegateExecution execution) throws Exception;
}
