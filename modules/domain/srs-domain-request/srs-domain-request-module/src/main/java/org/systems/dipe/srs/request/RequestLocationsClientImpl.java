package org.systems.dipe.srs.request;

import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.systems.dipe.srs.request.storage.RequestLocationsRepository;
import org.systems.dipe.srs.utils.TimeUtils;
import org.systems.dipe.srs.utils.UuidUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

@Service
@Transactional
@AllArgsConstructor
public class RequestLocationsClientImpl implements RequestLocationsClient {

    private final RequestLocationsRepository repository;

    @Override
    public void create(Collection<RequestLocation> locations) {
        if (CollectionUtils.isEmpty(locations)) {
            return;
        }
        prepareLocations(locations);
        repository.create(locations);
    }

    @Override
    public void update(Collection<RequestLocation> locations) {
        if (CollectionUtils.isEmpty(locations)) {
            return;
        }
        prepareLocations(locations);
        repository.update(locations);
    }

    @Override
    public Collection<RequestLocation> search(RequestLocationsSearch search) {
        if (CollectionUtils.isEmpty(search.getLocationIds())
                && CollectionUtils.isEmpty(search.getRequestsIds())) {
            return Collections.emptyList();
        }
        return repository.search(search);
    }

    private static void prepareLocations(Collection<RequestLocation> locations) {
        for (RequestLocation location : locations) {
            if (Objects.isNull(location.getLocationId())) {
                location.setLocationId(UuidUtils.newStr());
            }
            if (Objects.isNull(location.getCreated())) {
                location.setCreated(TimeUtils.now());
            }
        }
    }
}
