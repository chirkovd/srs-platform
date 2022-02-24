package org.systems.dipe.srs.search;

import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.systems.dipe.srs.search.storage.SearchSquadsRepository;
import org.systems.dipe.srs.utils.TimeUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

@Service
@Transactional
@AllArgsConstructor
public class SearchSquadsClientImpl implements SearchSquadsClient {

    private final SearchSquadsRepository repository;

    @Override
    public void create(Collection<SearchSquad> squads) {
        if (CollectionUtils.isEmpty(squads)) {
            return;
        }
        prepareSquads(squads);
        repository.create(squads);
    }

    @Override
    public void update(Collection<SearchSquad> squads) {
        if (CollectionUtils.isEmpty(squads)) {
            return;
        }
        prepareSquads(squads);
        repository.update(squads);
    }

    @Override
    public Collection<SearchSquad> search(SearchSquadsSearch search) {
        if (CollectionUtils.isEmpty(search.getSearchIds())
                && CollectionUtils.isEmpty(search.getSquadIds())) {
            return Collections.emptyList();
        }
        return repository.search(search);
    }

    private static void prepareSquads(Collection<SearchSquad> squads) {
        for (SearchSquad squad : squads) {
            if (Objects.isNull(squad.getSquadId())) {
                throw new IllegalArgumentException("Squad id is missing");
            }
            if (Objects.isNull(squad.getCreated())) {
                squad.setCreated(TimeUtils.now());
            }
        }
    }
}
