package org.systems.dipe.srs.squad;

import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.systems.dipe.srs.squad.storage.MembersRepository;
import org.systems.dipe.srs.utils.TimeUtils;
import org.systems.dipe.srs.utils.UuidUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

@Service
@Transactional
@AllArgsConstructor
public class MembersClientImpl implements MembersClient {

    private final MembersRepository repository;

    @Override
    public void create(Collection<Member> members) {
        if (CollectionUtils.isEmpty(members)) {
            return;
        }
        prepareMembers(members);
        repository.create(members);
    }

    @Override
    public void update(Collection<Member> members) {
        if (CollectionUtils.isEmpty(members)) {
            return;
        }
        prepareMembers(members);
        repository.update(members);
    }

    @Override
    public Collection<Member> search(MembersSearch search) {
        if (CollectionUtils.isEmpty(search.getMemberIds())
                && CollectionUtils.isEmpty(search.getSquadIds())
                && CollectionUtils.isEmpty(search.getVolunteerIds())) {
            return Collections.emptyList();
        }
        return repository.search(search);
    }

    private static void prepareMembers(Collection<Member> members) {
        for (Member member : members) {
            if (Objects.isNull(member.getMemberId())) {
                member.setMemberId(UuidUtils.newStr());
            }
            if (Objects.isNull(member.getCreated())) {
                member.setCreated(TimeUtils.now());
            }
        }
    }
}
