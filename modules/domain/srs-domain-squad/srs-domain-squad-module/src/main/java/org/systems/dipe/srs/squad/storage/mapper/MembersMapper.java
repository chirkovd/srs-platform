package org.systems.dipe.srs.squad.storage.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.systems.dipe.srs.mappers.CommonMapper;
import org.systems.dipe.srs.squad.Member;
import org.systems.dipe.srs.squad.jooq.tables.records.JMemberRecord;

import java.util.Collection;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface MembersMapper extends CommonMapper {

    JMemberRecord toJooq(Member member);

    Collection<JMemberRecord> toJooq(Collection<Member> members);

    Member fromJooq(JMemberRecord record);

}
