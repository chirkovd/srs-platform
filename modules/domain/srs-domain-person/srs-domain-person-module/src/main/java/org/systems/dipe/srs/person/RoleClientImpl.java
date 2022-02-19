package org.systems.dipe.srs.person;

import org.springframework.stereotype.Service;
import org.systems.dipe.srs.person.roles.Role;
import org.systems.dipe.srs.person.roles.RoleClient;
import org.systems.dipe.srs.person.roles.RoleSearch;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

@Service
public class RoleClientImpl implements RoleClient {

    @Override
    public Collection<Role> create(Collection<Role> roles) {
        return null;
    }

    @Override
    public Collection<Role> search(RoleSearch search) {
        return null;
    }

    @Override
    public void assign(Set<String> roleIds, String personId) {

    }

    @Override
    public Map<String, Set<Role>> fetchAssignment(Set<String> personIds) {
        return null;
    }
}
