package org.systems.dipe.srs.squad.storage;

import org.systems.dipe.srs.squad.Member;
import org.systems.dipe.srs.squad.MembersSearch;

import java.util.Collection;

public interface MembersRepository {

    void create(Collection<Member> members);

    void update(Collection<Member> members);

    Collection<Member> search(MembersSearch search);

}
