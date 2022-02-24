package org.systems.dipe.srs.search.storage.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.systems.dipe.srs.mappers.CommonMapper;
import org.systems.dipe.srs.search.SearchLocation;
import org.systems.dipe.srs.search.jooq.tables.records.JSearchLocationRecord;

import java.util.Collection;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface SearchLocationsMapper extends CommonMapper {

    @Mapping(target = "processId", source = "searchId")
    JSearchLocationRecord toJooq(SearchLocation location);

    Collection<JSearchLocationRecord> toJooq(Collection<SearchLocation> locations);

    @Mapping(target = "searchId", source = "processId")
    SearchLocation fromJooq(JSearchLocationRecord record);

}
