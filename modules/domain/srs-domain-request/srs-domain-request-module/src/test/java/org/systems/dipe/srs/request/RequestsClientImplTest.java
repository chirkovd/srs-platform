package org.systems.dipe.srs.request;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.systems.dipe.srs.SrsDbTest;
import org.systems.dipe.srs.request.config.TestConfig;
import org.systems.dipe.srs.utils.UuidUtils;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@SpringBootTest(classes = TestConfig.class)
class RequestsClientImplTest extends SrsDbTest {

    @Autowired
    private RequestsClient requestsClient;
    @Autowired
    private RequestItemsClient requestItemsClient;

    @Test
    void create() {
        Request request = new Request();
        request.setCustomerId(UuidUtils.newStr());
        request.setSupervisorId(UuidUtils.newStr());

        RequestItem requestItem1 = new RequestItem();
        requestItem1.setTargetId(UuidUtils.newStr());

        RequestItem requestItem2 = new RequestItem();
        requestItem2.setTargetId(UuidUtils.newStr());

        RequestLocation requestLocation = new RequestLocation();
        requestLocation.setLocationId(UuidUtils.newStr());

        request.setLocations(List.of(requestLocation));
        request.setItems(List.of(requestItem1, requestItem2));

        Request result = requestsClient.create(request);

        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getLocations()).hasSize(1);
        Assertions.assertThat(result.getItems()).hasSize(2);
    }

    @Test
    void update() {
        Request request = new Request();
        request.setCustomerId(UuidUtils.newStr());
        request.setSupervisorId(UuidUtils.newStr());

        RequestItem requestItem = new RequestItem();
        requestItem.setTargetId(UuidUtils.newStr());

        RequestLocation requestLocation = new RequestLocation();
        requestLocation.setLocationId(UuidUtils.newStr());

        request.setLocations(List.of(requestLocation));
        request.setItems(List.of(requestItem));

        Request result = requestsClient.create(request);

        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getLocations()).hasSize(1);
        Assertions.assertThat(result.getItems()).hasSize(1);

        // update
        String customerId = UuidUtils.newStr();
        request.setCustomerId(customerId);

        String supervisorId = UuidUtils.newStr();
        request.setSupervisorId(supervisorId);

        String targetId = UuidUtils.newStr();
        requestItem.setTargetId(targetId);

        String locationId = UuidUtils.newStr();
        requestLocation.setLocationId(locationId);

        result = requestsClient.update(request);

        // check
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result).hasFieldOrPropertyWithValue("customerId", customerId);
        Assertions.assertThat(result).hasFieldOrPropertyWithValue("supervisorId", supervisorId);
        Assertions.assertThat(result.getItems().get(0)).hasFieldOrPropertyWithValue("targetId", targetId);
        Assertions.assertThat(result.getLocations().get(0)).hasFieldOrPropertyWithValue("locationId", locationId);

    }

    @Test
    void approveFailed() {
        Request request = new Request();
        request.setRequestId(UuidUtils.newStr());
        request.setCustomerId(UuidUtils.newStr());
        request.setSupervisorId(UuidUtils.newStr());

        RequestItem requestItem = new RequestItem();
        requestItem.setTargetId(UuidUtils.newStr());
        request.setItems(List.of(requestItem));

        requestsClient.create(request);

        Assertions.assertThatThrownBy(() -> requestsClient.approve(request.getRequestId()));
    }

    @Test
    void approveSuccess() {
        Request request = new Request();
        request.setRequestId(UuidUtils.newStr());
        request.setCustomerId(UuidUtils.newStr());
        request.setSupervisorId(UuidUtils.newStr());

        RequestItem requestItem1 = new RequestItem();
        requestItem1.setItemId(UuidUtils.newStr());
        requestItem1.setTargetId(UuidUtils.newStr());

        RequestItem requestItem2 = new RequestItem();
        requestItem2.setItemId(UuidUtils.newStr());
        requestItem2.setTargetId(UuidUtils.newStr());

        request.setItems(List.of(requestItem1, requestItem2));

        requestsClient.create(request);

        requestItemsClient.approve(requestItem1.getItemId());
        requestItemsClient.dismiss(requestItem2.getItemId());
        requestsClient.approve(request.getRequestId());

        Collection<Request> requests = requestsClient.search(
                RequestsSearch.builder()
                        .requestIds(Set.of(request.getRequestId()))
                        .build());

        Assertions.assertThat(requests).hasSize(1);
        Assertions.assertThat(requests.iterator().next()).hasFieldOrProperty("approved");
    }
}