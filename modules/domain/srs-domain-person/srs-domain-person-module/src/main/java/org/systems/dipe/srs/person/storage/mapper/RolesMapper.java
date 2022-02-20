package org.systems.dipe.srs.person.storage.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.systems.dipe.srs.person.jooq.tables.records.JRoleRecord;
import org.systems.dipe.srs.person.roles.Role;
import org.systems.dipe.srs.person.roles.RoleAlias;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface RolesMapper {

    JRoleRecord toJooq(Role role);

    Role fromJooq(JRoleRecord record);

    default RoleAlias toRole(String alias) {
        return RoleAlias.valueOf(alias);
    }

    default String fromRole(RoleAlias alias) {
        return alias.name();
    }

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
