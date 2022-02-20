package org.systems.dipe.srs.person.roles;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public interface RoleClient {

    void create(Collection<Role> roles);

    Collection<Role> all();

    void assign(Set<String> roleIds, String personId);

    Map<String, Set<Role>> fetchAssignment(Set<String> personIds);
}
