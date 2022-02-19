package org.systems.dipe.srs.search.storage;

import lombok.AllArgsConstructor;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.systems.dipe.srs.search.SearchLocation;
import org.systems.dipe.srs.search.SearchProcess;
import org.systems.dipe.srs.search.SearchSquad;

@Repository
@Transactional
@AllArgsConstructor
public class SearchProcessJooqRepository implements SearchProcessRepository {

    private final DefaultDSLContext dsl;

    @Override
    public SearchProcess create(SearchProcess process) {
        return null;
    }

    @Override
    public SearchProcess update(SearchProcess process) {
        return null;
    }

    @Override
    public void addSquad(SearchSquad squad) {

    }

    @Override
    public void addLocation(SearchLocation location) {

    }
}
