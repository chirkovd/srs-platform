package org.systems.dipe.srs.person.storage.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.systems.dipe.srs.person.Person;
import org.systems.dipe.srs.person.jooq.tables.records.JPersonRecord;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface PeopleMapper {

    JPersonRecord toJooq(Person person);

    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "contacts", ignore = true)
    @Mapping(target = "identifications", ignore = true)
    Person fromJooq(JPersonRecord record);

    default LocalDateTime fromZdt(ZonedDateTime zdt) {
        return Optional.ofNullable(zdt).map(ZonedDateTime::toLocalDateTime).orElse(null);
    }

    default ZonedDateTime toZdt(LocalDateTime ldt) {
        return Optional.ofNullable(ldt).map(d -> d.atZone(ZoneId.of("UTC"))).orElse(null);
    }

    default UUID toStr(String uuid) {
        return Optional.ofNullable(uuid).map(UUID::fromString).orElse(null);
    }

    default String fromStr(UUID uuid) {
        return Optional.ofNullable(uuid).map(UUID::toString).orElse(null);
    }
}
