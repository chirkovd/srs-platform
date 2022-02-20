package org.systems.dipe.srs.person.storage;

import org.systems.dipe.srs.person.roles.Role;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public interface RoleRepository {

    void create(Collection<Role> roles);

    Collection<Role> all();

    void assign(Set<String> roleIds, String personId);

    Map<String, Set<Role>> fetchAssignment(Set<String> personIds);

}
