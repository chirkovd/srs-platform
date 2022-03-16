package org.systems.dipe.srs.person.storage.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.systems.dipe.srs.mappers.CommonMapper;
import org.systems.dipe.srs.person.Person;
import org.systems.dipe.srs.person.jooq.tables.records.JPersonRecord;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface PeopleMapper extends CommonMapper {

    JPersonRecord toJooq(Person person);

    @Mapping(target = "roleIds", ignore = true)
    @Mapping(target = "contacts", ignore = true)
    @Mapping(target = "identifications", ignore = true)
    Person fromJooq(JPersonRecord record);
}
