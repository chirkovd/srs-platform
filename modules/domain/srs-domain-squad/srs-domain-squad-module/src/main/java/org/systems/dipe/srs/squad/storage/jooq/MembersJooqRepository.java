package org.systems.dipe.srs.squad.storage.jooq;

import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.jooq.Condition;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.systems.dipe.srs.squad.Member;
import org.systems.dipe.srs.squad.MembersSearch;
import org.systems.dipe.srs.squad.jooq.tables.JMember;
import org.systems.dipe.srs.squad.storage.MembersRepository;
import org.systems.dipe.srs.squad.storage.mapper.MembersMapper;
import org.systems.dipe.srs.utils.GroupUtils;
import org.systems.dipe.srs.utils.UuidUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

@Repository
@Transactional
@AllArgsConstructor
public class MembersJooqRepository implements MembersRepository {

    private final DefaultDSLContext dsl;
    private final MembersMapper mapper;

    @Override
    public void create(Collection<Member> members) {
        dsl.batchInsert(mapper.toJooq(members)).execute();
    }

    @Override
    public void update(Collection<Member> members) {
        // clean up previous members
        Set<String> squadIds = GroupUtils.extractUnique(members, Member::getSquadId);
        dsl.deleteFrom(JMember.MEMBER)
                .where(JMember.MEMBER.SQUAD_ID.in(UuidUtils.fromStr(squadIds)))
                .execute();

        create(members);
    }

    @Override
    public Collection<Member> search(MembersSearch search) {
        Collection<Condition> conditions = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(search.getSquadIds())) {
            conditions.add(JMember.MEMBER.SQUAD_ID.in(UuidUtils.fromStr(search.getSquadIds())));
        }
        if (CollectionUtils.isNotEmpty(search.getMemberIds())) {
            conditions.add(JMember.MEMBER.MEMBER_ID.in(UuidUtils.fromStr(search.getMemberIds())));
        }
        if (CollectionUtils.isNotEmpty(search.getVolunteerIds())) {
            conditions.add(JMember.MEMBER.VOLUNTEER_ID.in(UuidUtils.fromStr(search.getVolunteerIds())));
        }
        return dsl.selectFrom(JMember.MEMBER)
                .where(conditions)
                .fetch()
                .map(mapper::fromJooq);
    }
}
