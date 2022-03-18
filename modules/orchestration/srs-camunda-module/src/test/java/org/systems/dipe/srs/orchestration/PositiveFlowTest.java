package org.systems.dipe.srs.orchestration;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.awaitility.Awaitility;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.Execution;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.systems.dipe.srs.SrsIntegrationTest;
import org.systems.dipe.srs.orchestration.config.TestConfig;
import org.systems.dipe.srs.orchestration.external.RequestsFacadeTestImpl;
import org.systems.dipe.srs.orchestration.external.SearchProcessFacadeTestImpl;
import org.systems.dipe.srs.orchestration.flows.search.messages.SearchSquadAssignMessage;
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
    @Autowired
    private SearchProcessFacadeTestImpl searchProcessFacade;

    @Test
    @Description("Run common positive flow - check camunda integration")
    public void flowTest() throws InterruptedException {
        String requestId = UuidUtils.newStr();
        String supervisorId = UuidUtils.newStr();

        submitRequest(requestId);

        assignRequest(requestId, supervisorId);
        approveRequest(requestId, supervisorId);

        String searchId = searchProcessFacade.getRequests().get(requestId);
        Assertions.assertThat(searchId).isNotNull();

        searchProcessCreated(searchId);

        assignSquad(searchId, requestId);

        completeRequest(searchId, requestId);
    }

    @Step("Assign squad for search process {searchId} and request {requestId}")
    private void assignSquad(String searchId, String requestId) {
        orchestrationClient.assignSquad(requestId, searchId);

        log.info("Wait for flow will be passed squad assigned message: {}", searchId);
        Awaitility.await()
                .pollInSameThread()
                .pollInterval(Duration.ofSeconds(5))
                .timeout(Duration.ofMinutes(1))
                .until(() -> {
                    log.info("Try to find process instance for subscription {}", searchId);

                    Execution execution = runtimeService.createExecutionQuery()
                            .messageEventSubscriptionName(SearchSquadAssignMessage.class.getSimpleName())
                            .processVariableValueEquals(SrsVariables.SEARCH_ID, searchId)
                            .active()
                            .singleResult();

                    return Objects.isNull(execution);
                });
    }

    @Step("Assign squad for search process {searchId}")
    private void searchProcessCreated(String searchId) {
        log.info("Wait for 'search process' flow will be created: {}", searchId);
        Awaitility.await()
                .pollInSameThread()
                .pollInterval(Duration.ofSeconds(5))
                .timeout(Duration.ofMinutes(1))
                .until(() -> {
                    log.info("Try to find process instance {}", searchId);

                    ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                            .processDefinitionKey(SrsVariables.SEARCH_FLOW)
                            .variableValueEquals(SrsVariables.SEARCH_ID, searchId)
                            .active().singleResult();

                    return Objects.nonNull(processInstance);
                });
    }

    @Step("Complete search process {searchId} and request {requestId}")
    private void completeRequest(String searchId, String requestId) {
        orchestrationClient.completeSearchProcess(requestId, searchId);

        log.info("Wait for 'search process' flow will be completed: {}", searchId);
        Awaitility.await()
                .pollInSameThread()
                .pollInterval(Duration.ofSeconds(5))
                .timeout(Duration.ofMinutes(1))
                .until(() -> {
                    log.info("Try to find process instance for search {}", searchId);

                    ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                            .processDefinitionKey(SrsVariables.SEARCH_FLOW)
                            .variableValueEquals(SrsVariables.SEARCH_ID, searchId)
                            .active().singleResult();

                    return Objects.isNull(processInstance);
                });

        log.info("Wait for 'request' flow will be completed: {}", requestId);
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

    @Step("Approve request {requestId} by supervisor {supervisorId}")
    private void approveRequest(String requestId, String supervisorId) {
        orchestrationClient.approveRequest(requestId, supervisorId);

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

    @Step("Assign request {requestId} to supervisor {supervisorId}")
    private void assignRequest(String requestId, String supervisorId) {
        orchestrationClient.assignRequest(requestId, supervisorId);

        log.info("Wait for request will be assigned: {}", requestId);
        Awaitility.await()
                .pollInSameThread()
                .pollInterval(Duration.ofSeconds(5))
                .timeout(Duration.ofMinutes(1))
                .until(() -> {
                    log.info("Try to check assigned request: {}", supervisorId);
                    return requestsFacade.getSupervisors().contains(supervisorId);
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