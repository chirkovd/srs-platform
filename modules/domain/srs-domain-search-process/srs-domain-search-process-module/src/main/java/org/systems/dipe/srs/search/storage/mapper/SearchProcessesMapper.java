package org.systems.dipe.srs.search.storage.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.systems.dipe.srs.mappers.CommonMapper;
import org.systems.dipe.srs.search.SearchProcess;
import org.systems.dipe.srs.search.jooq.tables.records.JSearchProcessRecord;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface SearchProcessesMapper extends CommonMapper {

    @Mapping(target = "processId", source = "searchId")
    JSearchProcessRecord toJooq(SearchProcess process);

    @Mapping(target = "searchId", source = "processId")
    @Mapping(target = "locations", ignore = true)
    @Mapping(target = "squads", ignore = true)
    SearchProcess fromJooq(JSearchProcessRecord record);

}
