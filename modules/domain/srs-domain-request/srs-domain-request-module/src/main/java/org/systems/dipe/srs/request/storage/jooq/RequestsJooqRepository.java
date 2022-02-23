package org.systems.dipe.srs.request.storage.jooq;

import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.jooq.Condition;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.systems.dipe.srs.request.Request;
import org.systems.dipe.srs.request.RequestsSearch;
import org.systems.dipe.srs.request.jooq.tables.JRequest;
import org.systems.dipe.srs.request.storage.RequestsRepository;
import org.systems.dipe.srs.request.storage.mapper.RequestsMapper;
import org.systems.dipe.srs.utils.TimeUtils;
import org.systems.dipe.srs.utils.UuidUtils;

import java.util.ArrayList;
import java.util.Collection;

@Repository
@Transactional
@AllArgsConstructor
public class RequestsJooqRepository implements RequestsRepository {

    private final DefaultDSLContext dsl;
    private final RequestsMapper mapper;

    @Override
    public void create(Request request) {
        dsl.insertInto(JRequest.REQUEST)
                .set(mapper.toJooq(request))
                .execute();
    }

    @Override
    public void update(Request request) {
        dsl.update(JRequest.REQUEST)
                .set(mapper.toJooq(request))
                .where(JRequest.REQUEST.REQUEST_ID.eq(UuidUtils.fromStr(request.getRequestId())))
                .execute();
    }

    @Override
    public void approve(String requestId) {
        dsl.update(JRequest.REQUEST)
                .set(JRequest.REQUEST.APPROVED, TimeUtils.fromZdt(TimeUtils.now()))
                .where(JRequest.REQUEST.REQUEST_ID.eq(UuidUtils.fromStr(requestId)))
                .execute();
    }

    @Override
    public Collection<Request> search(RequestsSearch search) {
        Collection<Condition> conditions = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(search.getRequestIds())) {
            conditions.add(JRequest.REQUEST.REQUEST_ID.in(UuidUtils.fromStr(search.getRequestIds())));
        }
        if (CollectionUtils.isNotEmpty(search.getCustomerIds())) {
            conditions.add(JRequest.REQUEST.CUSTOMER_ID.in(UuidUtils.fromStr(search.getCustomerIds())));
        }
        if (CollectionUtils.isNotEmpty(search.getSupervisorIds())) {
            conditions.add(JRequest.REQUEST.SUPERVISOR_ID.in(UuidUtils.fromStr(search.getSupervisorIds())));
        }
        return dsl.selectFrom(JRequest.REQUEST)
                .where(conditions)
                .fetch()
                .map(mapper::fromJooq);
    }
}
