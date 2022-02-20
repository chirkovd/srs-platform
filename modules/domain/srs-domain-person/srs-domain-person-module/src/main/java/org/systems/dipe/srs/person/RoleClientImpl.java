package org.systems.dipe.srs.person;

import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.systems.dipe.srs.person.roles.Role;
import org.systems.dipe.srs.person.roles.RoleClient;
import org.systems.dipe.srs.person.storage.RoleRepository;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

@Service
@AllArgsConstructor
public class RoleClientImpl implements RoleClient {

    private final RoleRepository roleRepository;

    @Override
    public void create(Collection<Role> roles) {
        if (CollectionUtils.isEmpty(roles)) {
            return;
        }
        for (Role role : roles) {
            if (Objects.isNull(role.getRoleId())) {
                role.setRoleId(UUID.randomUUID().toString());
            }
            if (Objects.isNull(role.getCreated())) {
                role.setCreated(ZonedDateTime.now(ZoneId.of("UTC")));
            }
        }
        roleRepository.create(roles);
    }

    @Override
    public Collection<Role> all() {
        return roleRepository.all();
    }

    @Override
    public void assign(Set<String> roleIds, String personId) {
        roleRepository.assign(roleIds, personId);
    }

    @Override
    public Map<String, Set<Role>> fetchAssignment(Set<String> personIds) {
        if (CollectionUtils.isEmpty(personIds)) {
            return Collections.emptyMap();
        }
        return roleRepository.fetchAssignment(personIds);
    }
}
