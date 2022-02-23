package org.systems.dipe.srs.squad;

import java.util.Collection;

public interface MembersClient {

    void create(Collection<Member> members);

    void update(Collection<Member> members);

    Collection<Member> search(MembersSearch search);
}
