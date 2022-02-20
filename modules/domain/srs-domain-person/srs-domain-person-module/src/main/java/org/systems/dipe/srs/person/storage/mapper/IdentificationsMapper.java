package org.systems.dipe.srs.person.storage.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.systems.dipe.srs.person.identifications.Identification;
import org.systems.dipe.srs.person.jooq.tables.records.JIdentificationRecord;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface IdentificationsMapper {

    @Mapping(target = "identityId", source = "id")
    @Mapping(target = "identityType", source = "type")
    JIdentificationRecord toJooq(Identification identification);

    @Mapping(target = "id", source = "identityId")
    @Mapping(target = "type", source = "identityType")
    Identification fromJooq(JIdentificationRecord record);

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
