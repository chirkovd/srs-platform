package org.systems.dipe.srs.search;

import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.systems.dipe.srs.search.storage.SearchLocationsRepository;
import org.systems.dipe.srs.utils.TimeUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

@Service
@Transactional
@AllArgsConstructor
public class SearchLocationsClientImpl implements SearchLocationsClient {

    private final SearchLocationsRepository repository;

    @Override
    public void create(Collection<SearchLocation> locations) {
        if (CollectionUtils.isEmpty(locations)) {
            return;
        }
        prepareLocations(locations);
        repository.create(locations);
    }

    @Override
    public void update(Collection<SearchLocation> locations) {
        if (CollectionUtils.isEmpty(locations)) {
            return;
        }
        prepareLocations(locations);
        repository.update(locations);
    }

    @Override
    public Collection<SearchLocation> search(SearchLocationsSearch search) {
        if (CollectionUtils.isEmpty(search.getSearchIds())
                && CollectionUtils.isEmpty(search.getLocationIds())) {
            return Collections.emptyList();
        }
        return repository.search(search);
    }

    private static void prepareLocations(Collection<SearchLocation> locations) {
        for (SearchLocation location : locations) {
            if (Objects.isNull(location.getLocationId())) {
                throw new IllegalArgumentException("Location id is missing");
            }
            if (Objects.isNull(location.getCreated())) {
                location.setCreated(TimeUtils.now());
            }
        }
    }
}
