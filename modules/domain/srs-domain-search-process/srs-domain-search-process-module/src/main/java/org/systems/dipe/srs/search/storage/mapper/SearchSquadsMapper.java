package org.systems.dipe.srs.search.storage.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.systems.dipe.srs.mappers.CommonMapper;
import org.systems.dipe.srs.search.SearchSquad;
import org.systems.dipe.srs.search.jooq.tables.records.JSearchSquadRecord;

import java.util.Collection;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface SearchSquadsMapper extends CommonMapper {

    @Mapping(target = "processId", source = "searchId")
    JSearchSquadRecord toJooq(SearchSquad squad);

    Collection<JSearchSquadRecord> toJooq(Collection<SearchSquad> squads);

    @Mapping(target = "searchId", source = "processId")
    SearchSquad fromJooq(JSearchSquadRecord record);
}
