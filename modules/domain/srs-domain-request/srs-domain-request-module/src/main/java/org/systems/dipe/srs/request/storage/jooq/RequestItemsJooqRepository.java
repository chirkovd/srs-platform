package org.systems.dipe.srs.request.storage.jooq;

import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.jooq.Condition;
import org.jooq.impl.DSL;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.systems.dipe.srs.request.RequestItem;
import org.systems.dipe.srs.request.RequestItemsSearch;
import org.systems.dipe.srs.request.jooq.tables.JRequestItem;
import org.systems.dipe.srs.request.jooq.tables.records.JRequestItemRecord;
import org.systems.dipe.srs.request.storage.RequestItemsRepository;
import org.systems.dipe.srs.request.storage.mapper.RequestItemsMapper;
import org.systems.dipe.srs.utils.TimeUtils;
import org.systems.dipe.srs.utils.UuidUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

@Repository
@Transactional
@AllArgsConstructor
public class RequestItemsJooqRepository implements RequestItemsRepository {

    private final DefaultDSLContext dsl;
    private final RequestItemsMapper mapper;

    @Override
    public void create(Collection<RequestItem> items) {
        dsl.batchInsert(mapper.toJooq(items)).execute();
    }

    @Override
    public void update(Collection<RequestItem> items) {
        for (RequestItem item : items) {
            JRequestItemRecord record = mapper.toJooq(item);
            dsl.insertInto(JRequestItem.REQUEST_ITEM)
                    .set(record)
                    .onDuplicateKeyUpdate()
                    .set(record)
                    .execute();
        }
    }

    @Override
    public Collection<RequestItem> search(RequestItemsSearch search) {
        Collection<Condition> conditions = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(search.getRequestItemIds())) {
            conditions.add(JRequestItem.REQUEST_ITEM.ITEM_ID.in(UuidUtils.fromStr(search.getRequestItemIds())));
        }
        if (CollectionUtils.isNotEmpty(search.getRequestIds())) {
            conditions.add(JRequestItem.REQUEST_ITEM.REQUEST_ID.in(UuidUtils.fromStr(search.getRequestIds())));
        }
        return dsl.selectFrom(JRequestItem.REQUEST_ITEM)
                .where(conditions)
                .fetch()
                .map(mapper::fromJooq);
    }

    @Override
    public void approve(String itemId) {
        dsl.update(JRequestItem.REQUEST_ITEM)
                .set(JRequestItem.REQUEST_ITEM.APPROVED, TimeUtils.fromZdt(TimeUtils.now()))
                .set(JRequestItem.REQUEST_ITEM.DISMISSED, DSL.val((LocalDateTime) null))
                .where(JRequestItem.REQUEST_ITEM.ITEM_ID.eq(UuidUtils.fromStr(itemId)))
                .execute();
    }

    @Override
    public void dismiss(String itemId) {
        dsl.update(JRequestItem.REQUEST_ITEM)
                .set(JRequestItem.REQUEST_ITEM.APPROVED, DSL.val((LocalDateTime) null))
                .set(JRequestItem.REQUEST_ITEM.DISMISSED, TimeUtils.fromZdt(TimeUtils.now()))
                .where(JRequestItem.REQUEST_ITEM.ITEM_ID.eq(UuidUtils.fromStr(itemId)))
                .execute();
    }
}
