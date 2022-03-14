package org.systems.dipe.srs.search.storage.jooq;

import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.jooq.Condition;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.systems.dipe.srs.search.SearchProcess;
import org.systems.dipe.srs.search.SearchProcessSearch;
import org.systems.dipe.srs.search.SearchProcessStatus;
import org.systems.dipe.srs.search.jooq.tables.JSearchProcess;
import org.systems.dipe.srs.search.jooq.tables.records.JSearchProcessRecord;
import org.systems.dipe.srs.search.storage.SearchProcessesRepository;
import org.systems.dipe.srs.search.storage.mapper.SearchProcessesMapper;
import org.systems.dipe.srs.utils.UuidUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

@Repository
@Transactional
@AllArgsConstructor
public class SearchProcessesJooqRepository implements SearchProcessesRepository {

    private final DefaultDSLContext dsl;
    private final SearchProcessesMapper mapper;

    @Override
    public void create(SearchProcess process) {
        dsl.insertInto(JSearchProcess.SEARCH_PROCESS)
                .set(mapper.toJooq(process))
                .execute();
    }

    @Override
    public void update(SearchProcess process) {
        JSearchProcessRecord record = mapper.toJooq(process);
        dsl.update(JSearchProcess.SEARCH_PROCESS)
                .set(record)
                .where(JSearchProcess.SEARCH_PROCESS.PROCESS_ID.eq(UUID.fromString(process.getSearchId())))
                .execute();
    }

    @Override
    public void updateStatus(String searchId, SearchProcessStatus status) {
        dsl.update(JSearchProcess.SEARCH_PROCESS)
                .set(JSearchProcess.SEARCH_PROCESS.STATUS, status.name())
                .where(JSearchProcess.SEARCH_PROCESS.PROCESS_ID.eq(UUID.fromString(searchId)))
                .execute();
    }

    @Override
    public Collection<SearchProcess> search(SearchProcessSearch search) {
        Collection<Condition> conditions = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(search.getSearchIds())) {
            conditions.add(JSearchProcess.SEARCH_PROCESS.PROCESS_ID.in(UuidUtils.fromStr(search.getSearchIds())));
        }
        if (CollectionUtils.isNotEmpty(search.getRequestIds())) {
            conditions.add(JSearchProcess.SEARCH_PROCESS.REQUEST_ID.in(UuidUtils.fromStr(search.getRequestIds())));
        }
        return dsl.selectFrom(JSearchProcess.SEARCH_PROCESS)
                .where(conditions)
                .fetch()
                .map(mapper::fromJooq);
    }
}
