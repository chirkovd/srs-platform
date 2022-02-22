package org.systems.dipe.srs.person.storage.jooq;

import lombok.AllArgsConstructor;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.systems.dipe.srs.person.jooq.tables.JRole;
import org.systems.dipe.srs.person.jooq.tables.JRoleLink;
import org.systems.dipe.srs.person.jooq.tables.records.JRoleLinkRecord;
import org.systems.dipe.srs.person.jooq.tables.records.JRoleRecord;
import org.systems.dipe.srs.person.roles.Role;
import org.systems.dipe.srs.person.storage.RolesRepository;
import org.systems.dipe.srs.person.storage.mapper.RolesMapper;
import org.systems.dipe.srs.utils.TimeUtils;
import org.systems.dipe.srs.utils.UuidUtils;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Repository
@Transactional
@AllArgsConstructor
public class RolesJooqRepository implements RolesRepository {

    private final DefaultDSLContext dsl;
    private final RolesMapper mapper;

    @Override
    public void create(Collection<Role> roles) {
        for (Role role : roles) {
            JRoleRecord record = mapper.toJooq(role);
            dsl.insertInto(JRole.ROLE)
                    .set(record)
                    .onConflictDoNothing()
                    .execute();
        }
    }

    @Override
    public Collection<Role> all() {
        return dsl.selectFrom(JRole.ROLE).fetch(mapper::fromJooq);
    }

    @Override
    public void assign(Set<String> roleIds, String personId) {
        dsl.deleteFrom(JRoleLink.ROLE_LINK)
                .where(JRoleLink.ROLE_LINK.PERSON_ID.eq(UuidUtils.fromStr(personId)))
                .execute();

        dsl.batchInsert(roleIds.stream()
                .map(id -> new JRoleLinkRecord(
                        UuidUtils.fromStr(id),
                        UuidUtils.fromStr(personId),
                        TimeUtils.fromZdt(TimeUtils.now())
                ))
                .collect(Collectors.toList())
        ).execute();
    }

    @Override
    public Map<String, Set<Role>> fetchAssignment(Set<String> personIds) {
        Map<String, Role> roleMap = all().stream().collect(
                Collectors.toMap(Role::getRoleId, Function.identity()));

        return dsl.selectFrom(JRoleLink.ROLE_LINK)
                .where(JRoleLink.ROLE_LINK.PERSON_ID.in(UuidUtils.fromStr(personIds)))
                .fetchInto(JRoleLinkRecord.class)
                .stream()
                .collect(Collectors.groupingBy(
                        r -> UuidUtils.toStr(r.getPersonId()),
                        Collectors.mapping(
                                r -> roleMap.get(UuidUtils.toStr(r.getRoleId())),
                                Collectors.toSet())
                        )
                );
    }
}
