package org.systems.dipe.srs.request.storage.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.systems.dipe.srs.mappers.CommonMapper;
import org.systems.dipe.srs.request.RequestItem;
import org.systems.dipe.srs.request.jooq.tables.records.JRequestItemRecord;

import java.util.Collection;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface RequestItemsMapper extends CommonMapper {

    JRequestItemRecord toJooq(RequestItem item);

    Collection<JRequestItemRecord> toJooq(Collection<RequestItem> items);

    RequestItem fromJooq(JRequestItemRecord record);
}
