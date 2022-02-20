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
import org.systems.dipe.srs.person.storage.RoleRepository;
import org.systems.dipe.srs.person.storage.mapper.RolesMapper;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Repository
@Transactional
@AllArgsConstructor
public class RoleJooqRepository implements RoleRepository {

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
                .where(JRoleLink.ROLE_LINK.PERSON_ID.eq(UUID.fromString(personId)))
                .execute();

        dsl.batchInsert(roleIds.stream()
                .map(id -> new JRoleLinkRecord(
                        UUID.fromString(id),
                        UUID.fromString(personId),
                        LocalDateTime.now(ZoneId.of("UTC"))
                ))
                .collect(Collectors.toList())
        ).execute();
    }

    @Override
    public Map<String, Set<Role>> fetchAssignment(Set<String> personIds) {
        Map<String, Role> roleMap = all().stream().collect(
                Collectors.toMap(Role::getRoleId, Function.identity()));

        return dsl.selectFrom(JRoleLink.ROLE_LINK)
                .where(JRoleLink.ROLE_LINK.PERSON_ID.in(
                        personIds.stream().map(UUID::fromString).collect(Collectors.toSet())
                ))
                .fetchInto(JRoleLinkRecord.class)
                .stream()
                .collect(Collectors.groupingBy(
                        r -> r.getPersonId().toString(),
                        Collectors.mapping(
                                r -> roleMap.get(r.getRoleId().toString()),
                                Collectors.toSet()))
                );
    }
}
