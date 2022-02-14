package org.systems.dipe.srs.person;

import org.springframework.stereotype.Service;
import org.systems.dipe.srs.person.roles.Role;
import org.systems.dipe.srs.person.roles.RoleClient;
import org.systems.dipe.srs.person.roles.RoleLink;
import org.systems.dipe.srs.person.roles.RoleSearch;

import java.util.Collection;
import java.util.Map;

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
    public RoleLink assign(String roleId, String personId) {
        return null;
    }

    @Override
    public Map<RoleLink, Role> find(String personId) {
        return null;
    }
}
