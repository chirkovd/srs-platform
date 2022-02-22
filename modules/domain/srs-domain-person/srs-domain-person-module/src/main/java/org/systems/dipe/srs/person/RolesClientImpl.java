package org.systems.dipe.srs.person;

import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.systems.dipe.srs.person.roles.Role;
import org.systems.dipe.srs.person.roles.RolesClient;
import org.systems.dipe.srs.person.storage.RolesRepository;
import org.systems.dipe.srs.utils.TimeUtils;
import org.systems.dipe.srs.utils.UuidUtils;

import java.util.*;

@Service
@AllArgsConstructor
public class RolesClientImpl implements RolesClient {

    private final RolesRepository rolesRepository;

    @Override
    public void create(Collection<Role> roles) {
        if (CollectionUtils.isEmpty(roles)) {
            return;
        }
        for (Role role : roles) {
            if (Objects.isNull(role.getRoleId())) {
                role.setRoleId(UuidUtils.newStr());
            }
            if (Objects.isNull(role.getCreated())) {
                role.setCreated(TimeUtils.now());
            }
        }
        rolesRepository.create(roles);
    }

    @Override
    public Collection<Role> all() {
        return rolesRepository.all();
    }

    @Override
    public void assign(Set<String> roleIds, String personId) {
        rolesRepository.assign(roleIds, personId);
    }

    @Override
    public Map<String, Set<Role>> fetchAssignment(Set<String> personIds) {
        if (CollectionUtils.isEmpty(personIds)) {
            return Collections.emptyMap();
        }
        return rolesRepository.fetchAssignment(personIds);
    }
}
