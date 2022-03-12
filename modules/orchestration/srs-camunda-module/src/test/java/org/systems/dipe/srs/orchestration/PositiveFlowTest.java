package org.systems.dipe.srs.orchestration;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.awaitility.Awaitility;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.systems.dipe.srs.SrsIntegrationTest;
import org.systems.dipe.srs.orchestration.config.TestConfig;
import org.systems.dipe.srs.orchestration.external.RequestsFacadeTestImpl;
import org.systems.dipe.srs.utils.UuidUtils;

import java.time.Duration;
import java.util.Objects;

@Slf4j
@SpringBootTest(classes = TestConfig.class)
public class PositiveFlowTest extends SrsIntegrationTest {

    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private OrchestrationClient orchestrationClient;
    @Autowired
    private RequestsFacadeTestImpl requestsFacade;

    @Test
    @Description("Run common positive flow - check camunda integration")
    public void flowTest() throws InterruptedException {
        String requestId = UuidUtils.newStr();

        submitRequest(requestId);

        approveRequest(requestId);

        completeRequest(requestId);
    }

    @Step("Complete request {requestId}")
    private void completeRequest(String requestId) {
        orchestrationClient.completeRequest(requestId);

        log.info("Wait for flow will be completed: {}", requestId);
        Awaitility.await()
                .pollInSameThread()
                .pollInterval(Duration.ofSeconds(5))
                .timeout(Duration.ofMinutes(1))
                .until(() -> {
                    log.info("Try to find process instance for request {}", requestId);

                    ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                            .processDefinitionKey(SrsVariables.REQUEST_FLOW)
                            .variableValueEquals(SrsVariables.REQUEST_ID, requestId)
                            .active().singleResult();

                    return Objects.isNull(processInstance);
                });
    }

    @Step("Approve request {requestId}")
    private void approveRequest(String requestId) {
        orchestrationClient.approveRequest(requestId);

        log.info("Wait for request will be approved: {}", requestId);
        Awaitility.await()
                .pollInSameThread()
                .pollInterval(Duration.ofSeconds(5))
                .timeout(Duration.ofMinutes(1))
                .until(() -> {
                    log.info("Try to check approved requests: {}", requestId);
                    return requestsFacade.getRequests().contains(requestId);
                });
    }

    @Step("Submit request {requestId}")
    private void submitRequest(String requestId) {
        orchestrationClient.submitRequest(requestId);

        log.info("Wait for flow will be created: {}", requestId);
        Awaitility.await()
                .pollInSameThread()
                .pollInterval(Duration.ofSeconds(5))
                .timeout(Duration.ofMinutes(1))
                .until(() -> {
                    log.info("Try to find process instance for request {}", requestId);

                    ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                            .processDefinitionKey(SrsVariables.REQUEST_FLOW)
                            .variableValueEquals(SrsVariables.REQUEST_ID, requestId)
                            .active().singleResult();

                    return Objects.nonNull(processInstance);
                });
    }
}