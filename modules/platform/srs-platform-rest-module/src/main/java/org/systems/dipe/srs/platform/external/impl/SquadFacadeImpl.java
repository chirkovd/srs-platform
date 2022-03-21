package org.systems.dipe.srs.platform.external.impl;

import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.systems.dipe.srs.platform.external.PeopleFacade;
import org.systems.dipe.srs.platform.external.SquadFacade;
import org.systems.dipe.srs.platform.mappers.SquadsDtoMapper;
import org.systems.dipe.srs.platform.squad.out.SquadOutDto;
import org.systems.dipe.srs.squad.*;
import org.systems.dipe.srs.utils.GroupUtils;

import java.util.*;
import java.util.stream.Collectors;

@Transactional
@AllArgsConstructor
@Component("platformSquadFacade")
@ConditionalOnBean(SquadsClient.class)
public class SquadFacadeImpl implements SquadFacade {

    private final SquadsClient squadsClient;
    private final MembersClient membersClient;
    private final SquadsDtoMapper mapper;

    private final PeopleFacade peopleFacade;

    @Override
    public List<SquadOutDto> search(Set<String> squadIds) {
        Collection<Squad> squads = squadsClient.search(
                SquadsSearch.builder()
                        .squadIds(squadIds)
                        .withDetails(true)
                        .build());

        return squads.stream().map(squad -> {
            SquadOutDto squadOutDto = mapper.toDto(squad);

            //TODO set equipments
            squadOutDto.setEquipments(Collections.emptyList());

            Set<String> volunteerIds = GroupUtils.extractUnique(squad.getMembers(), Member::getVolunteerId);
            squadOutDto.setMembers(peopleFacade.search(volunteerIds));

            return squadOutDto;
        }).collect(Collectors.toList());
    }

    @Override
    public void joinSquad(String squadId, String volunteerId) {
        Collection<Squad> squads = squadsClient.search(
                SquadsSearch.builder()
                        .squadIds(Set.of(squadId))
                        .withDetails(true)
                        .build());

        // TODO validate existence
        Squad squad = squads.iterator().next();
        List<Member> members = squad.getMembers();
        if (Objects.isNull(members)) {
            members = new ArrayList<>();
        }

        Member member = new Member();
        member.setVolunteerId(volunteerId);
        member.setSquadId(squadId);
        members.add(member);

        membersClient.update(members);
    }
}
