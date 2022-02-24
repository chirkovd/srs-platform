package org.systems.dipe.srs.search.storage.jooq;

import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.jooq.Condition;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.systems.dipe.srs.search.SearchLocation;
import org.systems.dipe.srs.search.SearchLocationsSearch;
import org.systems.dipe.srs.search.jooq.tables.JSearchLocation;
import org.systems.dipe.srs.search.storage.SearchLocationsRepository;
import org.systems.dipe.srs.search.storage.mapper.SearchLocationsMapper;
import org.systems.dipe.srs.utils.GroupUtils;
import org.systems.dipe.srs.utils.UuidUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

@Repository
@Transactional
@AllArgsConstructor
public class SearchLocationsJooqRepository implements SearchLocationsRepository {

    private final DefaultDSLContext dsl;
    private final SearchLocationsMapper mapper;

    @Override
    public void create(Collection<SearchLocation> locations) {
        dsl.batchInsert(mapper.toJooq(locations)).execute();
    }

    @Override
    public void update(Collection<SearchLocation> locations) {
        Set<String> searchIds = GroupUtils.extractUnique(locations, SearchLocation::getSearchId);
        dsl.deleteFrom(JSearchLocation.SEARCH_LOCATION)
                .where(JSearchLocation.SEARCH_LOCATION.PROCESS_ID.in(UuidUtils.fromStr(searchIds)))
                .execute();

        create(locations);
    }

    @Override
    public Collection<SearchLocation> search(SearchLocationsSearch search) {
        Collection<Condition> conditions = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(search.getSearchIds())) {
            conditions.add(JSearchLocation.SEARCH_LOCATION.PROCESS_ID.in(UuidUtils.fromStr(search.getSearchIds())));
        }
        if (CollectionUtils.isNotEmpty(search.getLocationIds())) {
            conditions.add(JSearchLocation.SEARCH_LOCATION.LOCATION_ID.in(UuidUtils.fromStr(search.getLocationIds())));
        }
        return dsl.selectFrom(JSearchLocation.SEARCH_LOCATION)
                .where(conditions)
                .fetch()
                .map(mapper::fromJooq);
    }
}
