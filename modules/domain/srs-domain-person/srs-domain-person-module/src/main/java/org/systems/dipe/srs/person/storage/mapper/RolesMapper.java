package org.systems.dipe.srs.person.storage.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.systems.dipe.srs.mappers.CommonMapper;
import org.systems.dipe.srs.person.jooq.tables.records.JRoleRecord;
import org.systems.dipe.srs.person.roles.Role;
import org.systems.dipe.srs.person.roles.RoleAlias;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface RolesMapper extends CommonMapper {

    JRoleRecord toJooq(Role role);

    Role fromJooq(JRoleRecord record);

    default RoleAlias toRole(String alias) {
        return RoleAlias.valueOf(alias);
    }

    default String fromRole(RoleAlias alias) {
        return alias.name();
    }
}
