package org.systems.dipe.srs.request;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.systems.dipe.srs.request.storage.RequestsRepository;
import org.systems.dipe.srs.utils.GroupUtils;
import org.systems.dipe.srs.utils.TimeUtils;
import org.systems.dipe.srs.utils.UuidUtils;

import java.util.*;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class RequestsClientImpl implements RequestsClient {

    private final RequestsRepository repository;
    private final RequestItemsClient itemsClient;
    private final RequestLocationsClient locationsClient;

    @Override
    public Request create(Request request) {
        if (Objects.isNull(request.getRequestId())) {
            request.setRequestId(UuidUtils.newStr());
        }
        if (Objects.isNull(request.getCreated())) {
            request.setCreated(TimeUtils.now());
        }
        repository.create(request);
        if (CollectionUtils.isNotEmpty(request.getItems())) {
            for (RequestItem item : request.getItems()) {
                item.setRequestId(request.getRequestId());
            }
            itemsClient.create(request.getItems());
        }
        if (CollectionUtils.isNotEmpty(request.getLocations())) {
            for (RequestLocation location : request.getLocations()) {
                location.setRequestId(request.getRequestId());
            }
            locationsClient.create(request.getLocations());
        }
        return find(request.getRequestId());
    }

    @Override
    public Request update(Request request) {
        repository.update(request);
        if (CollectionUtils.isNotEmpty(request.getItems())) {
            for (RequestItem item : request.getItems()) {
                item.setRequestId(request.getRequestId());
            }
            itemsClient.update(request.getItems());
        }
        if (CollectionUtils.isNotEmpty(request.getLocations())) {
            for (RequestLocation location : request.getLocations()) {
                location.setRequestId(request.getRequestId());
            }
            locationsClient.update(request.getLocations());
        }
        return find(request.getRequestId());
    }

    @Override
    public void approve(String requestId) {
        Request request = find(requestId);
        if (CollectionUtils.isNotEmpty(request.getItems()) && request.getItems().stream()
                .anyMatch(item -> Objects.isNull(item.getApproved()) && Objects.isNull(item.getDismissed()))) {
            throw new IllegalArgumentException("Not processed item is presented in the request");
        }
        repository.approve(requestId);
    }

    @Override
    public Collection<Request> search(RequestsSearch search) {
        Collection<Request> requests = repository.search(search);

        if (!requests.isEmpty() && search.isWithDetails()) {
            Set<String> requestIds = GroupUtils.extractUnique(requests, Request::getRequestId);

            Collection<RequestItem> items = itemsClient.search(
                    RequestItemsSearch.builder()
                            .requestIds(requestIds)
                            .build()
            );
            Collection<RequestLocation> locations = locationsClient.search(
                    RequestLocationsSearch.builder()
                            .requestsIds(requestIds)
                            .build()
            );

            Map<String, List<RequestItem>> itemsMap = GroupUtils.groupMultipleBy(items, RequestItem::getRequestId);
            Map<String, List<RequestLocation>> locationsMap = GroupUtils.groupMultipleBy(locations, RequestLocation::getRequestId);

            for (Request request : requests) {
                request.setItems(itemsMap.get(request.getRequestId()));
                request.setLocations(locationsMap.get(request.getRequestId()));
            }
        }

        return requests;
    }

    private Request find(String requestId) {
        Collection<Request> requests = search(RequestsSearch.builder()
                .requestIds(Set.of(requestId))
                .withDetails(true)
                .build());
        if (!requests.isEmpty()) {
            return requests.iterator().next();
        } else {
            log.error("Cannot find new request {}", requestId);
            throw new IllegalArgumentException("Cannot find request");
        }
    }
}
