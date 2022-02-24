package org.systems.dipe.srs.search.storage.jooq;

import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.jooq.Condition;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.systems.dipe.srs.search.SearchSquad;
import org.systems.dipe.srs.search.SearchSquadsSearch;
import org.systems.dipe.srs.search.jooq.tables.JSearchSquad;
import org.systems.dipe.srs.search.storage.SearchSquadsRepository;
import org.systems.dipe.srs.search.storage.mapper.SearchSquadsMapper;
import org.systems.dipe.srs.utils.GroupUtils;
import org.systems.dipe.srs.utils.UuidUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

@Repository
@Transactional
@AllArgsConstructor
public class SearchSquadsJooqRepository implements SearchSquadsRepository {

    private final DefaultDSLContext dsl;
    private final SearchSquadsMapper mapper;

    @Override
    public void create(Collection<SearchSquad> squads) {
        dsl.batchInsert(mapper.toJooq(squads)).execute();
    }

    @Override
    public void update(Collection<SearchSquad> squads) {
        Set<String> searchIds = GroupUtils.extractUnique(squads, SearchSquad::getSearchId);
        dsl.deleteFrom(JSearchSquad.SEARCH_SQUAD)
                .where(JSearchSquad.SEARCH_SQUAD.PROCESS_ID.in(UuidUtils.fromStr(searchIds)))
                .execute();

        create(squads);
    }

    @Override
    public Collection<SearchSquad> search(SearchSquadsSearch search) {
        Collection<Condition> conditions = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(search.getSearchIds())) {
            conditions.add(JSearchSquad.SEARCH_SQUAD.PROCESS_ID.in(UuidUtils.fromStr(search.getSearchIds())));
        }
        if (CollectionUtils.isNotEmpty(search.getSquadIds())) {
            conditions.add(JSearchSquad.SEARCH_SQUAD.SQUAD_ID.in(UuidUtils.fromStr(search.getSquadIds())));
        }
        return dsl.selectFrom(JSearchSquad.SEARCH_SQUAD)
                .where(conditions)
                .fetch()
                .map(mapper::fromJooq);
    }
}
