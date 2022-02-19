package org.systems.dipe.srs.search;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.systems.dipe.srs.search.storage.SearchProcessRepository;

@Service
@AllArgsConstructor
public class SearchProcessClientImpl implements SearchProcessClient {

    private final SearchProcessRepository searchProcessRepository;

    @Override
    public SearchProcess create(SearchProcess process) {
        return searchProcessRepository.create(process);
    }

    @Override
    public SearchProcess update(SearchProcess process) {
        return searchProcessRepository.update(process);
    }

    @Override
    public void addSquad(SearchSquad squad) {
        searchProcessRepository.addSquad(squad);
    }

    @Override
    public void addLocation(SearchLocation location) {
        searchProcessRepository.addLocation(location);
    }
}
