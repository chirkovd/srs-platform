package org.systems.dipe.srs.orchestration.external.impl;

import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;
import org.systems.dipe.srs.orchestration.external.SearchProcessFacade;
import org.systems.dipe.srs.search.SearchProcess;
import org.systems.dipe.srs.search.SearchProcessClient;
import org.systems.dipe.srs.search.SearchProcessSearch;
import org.systems.dipe.srs.search.SearchProcessStatus;

import java.util.Collection;
import java.util.Set;

@Component
@AllArgsConstructor
@ConditionalOnBean(SearchProcessClient.class)
public class SearchProcessFacadeImpl implements SearchProcessFacade {

    private final SearchProcessClient client;

    @Override
    public SearchProcess create(SearchProcess process) {
        return client.create(process);
    }

    @Override
    public SearchProcess update(SearchProcess process) {
        return client.update(process);
    }

    @Override
    public SearchProcess find(String searchId) {
        Collection<SearchProcess> processes = client.search(
                SearchProcessSearch.builder()
                        .searchIds(Set.of(searchId))
                        .withDetails(true)
                        .build()
        );
        return processes.isEmpty() ? null : processes.iterator().next();
    }

    @Override
    public void updateStatus(String searchId, SearchProcessStatus status) {
        client.updateStatus(searchId, status);
    }
}
