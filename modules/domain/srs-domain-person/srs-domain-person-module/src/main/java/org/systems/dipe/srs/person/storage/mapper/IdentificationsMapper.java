package org.systems.dipe.srs.person.storage.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.systems.dipe.srs.mappers.CommonMapper;
import org.systems.dipe.srs.person.identifications.Identification;
import org.systems.dipe.srs.person.jooq.tables.records.JIdentificationRecord;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface IdentificationsMapper extends CommonMapper {

    @Mapping(target = "identityId", source = "id")
    @Mapping(target = "identityType", source = "type")
    JIdentificationRecord toJooq(Identification identification);

    @Mapping(target = "id", source = "identityId")
    @Mapping(target = "type", source = "identityType")
    Identification fromJooq(JIdentificationRecord record);
}
