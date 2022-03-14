package org.systems.dipe.srs.search;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.systems.dipe.srs.search.storage.SearchProcessesRepository;
import org.systems.dipe.srs.utils.GroupUtils;
import org.systems.dipe.srs.utils.TimeUtils;
import org.systems.dipe.srs.utils.UuidUtils;

import java.util.*;

@Slf4j
@Service
@AllArgsConstructor
public class SearchProcessesClientImpl implements SearchProcessClient {

    private final SearchProcessesRepository repository;
    private final SearchLocationsClient locationsClient;
    private final SearchSquadsClient squadsClient;

    @Override
    public SearchProcess create(SearchProcess process) {
        if (Objects.isNull(process.getSearchId())) {
            process.setSearchId(UuidUtils.newStr());
        }
        if (Objects.isNull(process.getCreated())) {
            process.setCreated(TimeUtils.now());
        }
        repository.create(process);

        if (CollectionUtils.isNotEmpty(process.getLocations())) {
            for (SearchLocation location : process.getLocations()) {
                location.setSearchId(process.getSearchId());
            }
            locationsClient.create(process.getLocations());
        }

        if (CollectionUtils.isNotEmpty(process.getSquads())) {
            for (SearchSquad squad : process.getSquads()) {
                squad.setSearchId(process.getSearchId());
            }
            squadsClient.create(process.getSquads());
        }

        return find(process.getSearchId());
    }

    @Override
    public SearchProcess update(SearchProcess process) {
        repository.update(process);

        if (CollectionUtils.isNotEmpty(process.getLocations())) {
            for (SearchLocation location : process.getLocations()) {
                location.setSearchId(process.getSearchId());
            }
            locationsClient.update(process.getLocations());
        }

        if (CollectionUtils.isNotEmpty(process.getSquads())) {
            for (SearchSquad squad : process.getSquads()) {
                squad.setSearchId(process.getSearchId());
            }
            squadsClient.update(process.getSquads());
        }

        return find(process.getSearchId());
    }

    @Override
    public void updateStatus(String searchId, SearchProcessStatus status) {
        repository.updateStatus(searchId, status);
    }

    @Override
    public Collection<SearchProcess> search(SearchProcessSearch search) {
        if (CollectionUtils.isEmpty(search.getSearchIds())
                && CollectionUtils.isEmpty(search.getRequestIds())) {
            return Collections.emptyList();
        }
        Collection<SearchProcess> processes = repository.search(search);

        if (!processes.isEmpty() && search.isWithDetails()) {
            Set<String> searchIds = GroupUtils.extractUnique(processes, SearchProcess::getSearchId);

            Map<String, List<SearchLocation>> locationsMap = GroupUtils.groupMultipleBy(
                    locationsClient.search(
                            SearchLocationsSearch.builder()
                                    .searchIds(searchIds)
                                    .build()
                    ),
                    SearchLocation::getSearchId
            );

            Map<String, List<SearchSquad>> squadsMap = GroupUtils.groupMultipleBy(
                    squadsClient.search(
                            SearchSquadsSearch.builder()
                                    .searchIds(searchIds)
                                    .build()
                    ),
                    SearchSquad::getSearchId
            );

            for (SearchProcess process : processes) {
                process.setLocations(locationsMap.get(process.getSearchId()));
                process.setSquads(squadsMap.get(process.getSearchId()));
            }
        }

        return processes;
    }

    private SearchProcess find(String searchId) {
        Collection<SearchProcess> processes = search(
                SearchProcessSearch.builder()
                        .searchIds(Set.of(searchId))
                        .withDetails(true)
                        .build()
        );
        if (!processes.isEmpty()) {
            return processes.iterator().next();
        } else {
            log.error("Cannot find search process {}", searchId);
            throw new IllegalArgumentException("Cannot find process");
        }
    }
}
