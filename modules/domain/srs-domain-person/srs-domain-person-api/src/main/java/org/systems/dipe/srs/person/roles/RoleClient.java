package org.systems.dipe.srs.person.roles;

import java.util.Collection;
import java.util.Map;

public interface RoleClient {

    Collection<Role> create(Collection<Role> roles);

    Collection<Role> search(RoleSearch search);

    void assign(String roleId, String personId);
}
