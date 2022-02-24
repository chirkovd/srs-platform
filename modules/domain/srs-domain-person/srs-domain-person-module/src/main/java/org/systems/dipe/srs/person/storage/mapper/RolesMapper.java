package org.systems.dipe.srs.person.storage.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.systems.dipe.srs.mappers.CommonMapper;
import org.systems.dipe.srs.person.jooq.tables.records.JRoleRecord;
import org.systems.dipe.srs.person.roles.Role;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface RolesMapper extends CommonMapper {

    JRoleRecord toJooq(Role role);

    Role fromJooq(JRoleRecord record);
}
