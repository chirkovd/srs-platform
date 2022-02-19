package org.systems.dipe.srs.person.storage.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.systems.dipe.srs.person.Person;
import org.systems.dipe.srs.person.jooq.tables.records.JPersonRecord;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN) //TODO set ERROR
public interface PeopleMapper {

    JPersonRecord toJooq(Person person);

    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "contacts", ignore = true)
    @Mapping(target = "identifications", ignore = true)
    Person fromJooq(JPersonRecord record);

    default LocalDateTime fromZdt(ZonedDateTime zdt) {
        return zdt.toLocalDateTime();
    }

    default ZonedDateTime toZdt(LocalDateTime ldt) {
        return ldt.atZone(ZoneId.of("UTC"));
    }

    default UUID toStr(String uuid) {
        return UUID.fromString(uuid);
    }

    default String fromStr(UUID uuid) {
        return uuid.toString();
    }
}
