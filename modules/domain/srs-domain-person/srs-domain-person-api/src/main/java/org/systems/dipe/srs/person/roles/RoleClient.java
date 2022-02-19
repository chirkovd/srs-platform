package org.systems.dipe.srs.person.roles;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public interface RoleClient {

    Collection<Role> create(Collection<Role> roles);

    Collection<Role> search(RoleSearch search);

    void assign(Set<String> roleIds, String personId);

    Map<String, Set<Role>> fetchAssignment(Set<String> personIds);
}
